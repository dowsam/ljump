package cn.com.solex.ljump.entity.system;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 计划任务
 * 
 * @author l.xue.nong
 * 
 */
public class Qrtz_Triggers_Job_Details implements Serializable {
	private static final long serialVersionUID = -8496387046879775291L;
	private String trigger_name;
	private String trigger_group;
	private String job_name;
	private String job_group;
	private Boolean is_volatile;
	private String description;
	private Long next_fire_time;
	private Long prev_fire_time;
	private Integer priority;
	private String trigger_state;
	private String trigger_type;
	private Long start_time;
	private Long END_TIME;
	private String calendar_name;
	private Long misfire_instr;

	private String job_details_description;
	private String job_details_job_class_name;

	public String getTrigger_name() {
		return trigger_name;
	}

	public void setTrigger_name(String trigger_name) {
		this.trigger_name = trigger_name;
	}

	public String getTrigger_group() {
		return trigger_group;
	}

	public void setTrigger_group(String trigger_group) {
		this.trigger_group = trigger_group;
	}

	public String getJob_name() {
		return job_name;
	}

	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}

	public String getJob_group() {
		return job_group;
	}

	public void setJob_group(String job_group) {
		this.job_group = job_group;
	}

	public Boolean getIs_volatile() {
		return is_volatile;
	}

	public void setIs_volatile(Boolean is_volatile) {
		this.is_volatile = is_volatile;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getNext_fire_time() {
		return next_fire_time;
	}

	public void setNext_fire_time(Long next_fire_time) {
		this.next_fire_time = next_fire_time;
	}

	public Long getPrev_fire_time() {
		return prev_fire_time;
	}

	public void setPrev_fire_time(Long prev_fire_time) {
		this.prev_fire_time = prev_fire_time;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getTrigger_state() {
		return trigger_state;
	}

	public void setTrigger_state(String trigger_state) {
		this.trigger_state = trigger_state;
	}

	public String getTrigger_type() {
		return trigger_type;
	}

	public void setTrigger_type(String trigger_type) {
		this.trigger_type = trigger_type;
	}

	public Long getStart_time() {
		return start_time;
	}

	public void setStart_time(Long start_time) {
		this.start_time = start_time;
	}

	public Long getEND_TIME() {
		return END_TIME;
	}

	public void setEND_TIME(Long eND_TIME) {
		END_TIME = eND_TIME;
	}

	public String getCalendar_name() {
		return calendar_name;
	}

	public void setCalendar_name(String calendar_name) {
		this.calendar_name = calendar_name;
	}

	public Long getMisfire_instr() {
		return misfire_instr;
	}

	public void setMisfire_instr(Long misfire_instr) {
		this.misfire_instr = misfire_instr;
	}

	public String getJob_details_description() {
		return job_details_description;
	}

	public void setJob_details_description(String job_details_description) {
		this.job_details_description = job_details_description;
	}

	public String getJob_details_job_class_name() {
		return job_details_job_class_name;
	}

	public void setJob_details_job_class_name(String job_details_job_class_name) {
		this.job_details_job_class_name = job_details_job_class_name;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
