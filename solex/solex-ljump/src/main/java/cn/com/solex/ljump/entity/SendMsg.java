package cn.com.solex.ljump.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class SendMsg implements Serializable {

	private static final long serialVersionUID = -7293744406298408697L;
	private Integer seq;// 序列号
	private String srcNum;// 主叫号码
	private String[] dstNums;// 接收号码，必须少于100个
	private String scheduleTime;// 定时发送时间(yyyymmddhhmmss)，置为空表示为即时发送
	private Boolean needRpt;// 是否返回状态报告 0-否，1-是
	private String msgStr;// 短信内容

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getSrcNum() {
		return srcNum;
	}

	public void setSrcNum(String srcNum) {
		this.srcNum = srcNum;
	}

	public String[] getDstNums() {
		return dstNums;
	}

	public void setDstNums(String[] dstNums) {
		this.dstNums = dstNums;
	}

	public String getScheduleTime() {
		return scheduleTime;
	}

	public void setScheduleTime(String scheduleTime) {
		this.scheduleTime = scheduleTime;
	}

	public Boolean getNeedRpt() {
		return needRpt;
	}

	public void setNeedRpt(Boolean needRpt) {
		this.needRpt = needRpt;
	}

	public String getMsgStr() {
		return msgStr;
	}

	public void setMsgStr(String msgStr) {
		this.msgStr = msgStr;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (!(obj instanceof SendMsg))
			return false;
		SendMsg tempMSg = (SendMsg) obj;
		if (getSeq() == null)
			super.equals(obj);
		return this.getSeq().equals(tempMSg.getSeq());
	}

}
