package com.hzvtc.starrynight.response;

public class Response {
	/** 返回信息码*/
	private String rspCode="000000";
	/** 返回信息内容*/
	private String rspMsg="操作成功";

	public Response() {
	}
	
	public Response(EmExceptionMsg msg){
		this.rspCode=msg.getErrCode();
		this.rspMsg=msg.getErrCode();
	}
	
	public Response(String rspCode) {
		this.rspCode = rspCode;
		this.rspMsg = "";
	}

	public Response(String rspCode, String rspMsg) {
		this.rspCode = rspCode;
		this.rspMsg = rspMsg;
	}
	public String getRspCode() {
		return rspCode;
	}
	public void setRspCode(String rspCode) {
		this.rspCode = rspCode;
	}
	public String getRspMsg() {
		return rspMsg;
	}
	public void setRspMsg(String rspMsg) {
		this.rspMsg = rspMsg;
	}

	@Override
	public String toString() {
		return "Response{" +
				"rspCode='" + rspCode + '\'' +
				", rspMsg='" + rspMsg + '\'' +
				'}';
	}
}
