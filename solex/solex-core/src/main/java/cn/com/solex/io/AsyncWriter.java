package cn.com.solex.io;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.solex.io.AsyncExceptinHandler.DefaultAsyncExceptinHandler;
/**
 *　异步的writer
 * 使用示例:
 * <pre>
 * 	BufferedWriter writer = new BufferedWriter(new AsyncWriter(new FileWriter("c:/debug.log")));
 *	writer.write("xxxxx");
 * </pre>
 * @author l.xue.nong
 *
 */
public class AsyncWriter extends Writer {

	private static Logger log = LoggerFactory.getLogger(AsyncWriter.class);

	private static final int DEFAULT_QUEUE_CAPACITY = 50000;
	private final static char[] CLOSED_SIGNEL = new char[0];

	private Writer out;
	private DataProcessorThread dataProcessor;
	private boolean isClosed = false;
	private boolean isStartd = false;
	private BlockingQueue<char[]> queue;

	private AsyncExceptinHandler asyncExceptinHandler = new DefaultAsyncExceptinHandler();
	private static long threadSeqNumber;

	private static synchronized long nextThreadID() {
		return ++threadSeqNumber;
	}

	private class DataProcessorThread extends Thread {

		private boolean enabled = true;
		private boolean hasRuned = false;

		DataProcessorThread() {
			super("AsyncWriter.DataProcessorThread-" + nextThreadID());
			setDaemon(true);
		}

		public void run() {
			hasRuned = true;
			while (this.enabled || !queue.isEmpty()) {

				char[] buf;
				try {
					buf = queue.take();
				} catch (InterruptedException e) {
					continue;
				}

				if (buf == CLOSED_SIGNEL) {
					return;
				}

				try {
					out.write(buf);
				} catch (IOException e) {
					asyncExceptinHandler.handle(e);
				}
			}
		}
	}

	public AsyncWriter(Writer out) {
		this(out, DEFAULT_QUEUE_CAPACITY, Thread.NORM_PRIORITY + 1);
	}

	public AsyncWriter(Writer out, int queueCapacity) {
		this(out, queueCapacity, Thread.NORM_PRIORITY + 1);
	}

	@SuppressWarnings("rawtypes")
	public AsyncWriter(Writer out, int queueCapacity,
			int dataProcesserThreadPriority) {
		this(out, new ArrayBlockingQueue(queueCapacity),
				dataProcesserThreadPriority);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AsyncWriter(Writer out, BlockingQueue queue,
			int dataProcesserThreadPriority) {
		if (out == null)
			throw new NullPointerException();
		if (queue == null)
			throw new NullPointerException();

		this.queue = queue;
		this.dataProcessor = new DataProcessorThread();
		if (dataProcesserThreadPriority != Thread.NORM_PRIORITY) {
			this.dataProcessor.setPriority(dataProcesserThreadPriority);
		}
		this.out = out;
	}

	public AsyncWriter(Writer out, AsyncExceptinHandler handler) {
		this(out);
		setAsyncExceptinHandler(handler);
	}

	public void start() {
		this.dataProcessor.start();
		isStartd = true;
	}

	public void write(char[] buf, int offset, int length) throws IOException {
		if (!isStartd)
			throw new IOException("must start() before wirte()");
		synchronized (lock) {
			if (isClosed)
				throw new IOException("already closed");
			try {
				queue.put(BufferCopyUtils.copyBuffer(buf, offset, length));
			} catch (InterruptedException e) {
				throw new IOException(
						"AsyncWriter occer InterruptedException error");
			}
		}
	}

	public void close() throws IOException {
		synchronized (lock) {
			try {
				isClosed = true;
				dataProcessor.enabled = false;
				if (queue.isEmpty()) {
					queue.offer(CLOSED_SIGNEL);
				}

				try {
					dataProcessor.join();
				} catch (InterruptedException e) {
					// ignore
				}

				if (!dataProcessor.hasRuned) {
					dataProcessor.run();
				}
			} finally {
				out.close();
			}
		}
	}

	public void flush() throws IOException {
	}

	public void forceFlush() throws IOException {
		synchronized (lock) {
			// wait until dataQueue is empty, before calling flush()
			while (queue.size() > 0) {
				try {
					wait(100);
				} catch (InterruptedException e) {
				}
			}
			out.flush();
		}
	}

	protected void finalize() throws Throwable {
		super.finalize();
		if (!isClosed) {
			log.warn("AsyncWriter not close:" + this);
			close();
		}
	}

	public void setAsyncExceptinHandler(
			AsyncExceptinHandler asyncExceptinHandler) {
		if (asyncExceptinHandler == null)
			throw new NullPointerException();
		this.asyncExceptinHandler = asyncExceptinHandler;
	}

}
