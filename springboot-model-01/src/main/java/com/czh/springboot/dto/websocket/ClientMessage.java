package com.czh.springboot.dto.websocket;
/**  
* <p>Title: ClientMessage.java</p>  
* <p>Description: </p>  
* <p>Copyright: Copyright (c) 2018</p>  
* <p>Company: www.chenzhehao.com</p>  
* @author chenzhehao  
* @date 2018年4月9日  
* @version 1.0  
*/
public class ClientMessage {
	private String name;
	private String Id;
	private String information;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
}
