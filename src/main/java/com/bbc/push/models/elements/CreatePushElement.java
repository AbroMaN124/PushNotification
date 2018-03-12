package com.bbc.push.models.elements;

/**
 * A create push element that abides to https://docs.pushbullet.com/#create-push
 *
 * @author Ahmad A. Furqan
 *
 */
public class CreatePushElement {

  private String type;
  private String title;
  private String body;
  private String url;
  private String file_name;
  private String file_type;
  private String file_url;
  private String source_device_iden;
  private String device_iden;
  private String client_iden;
  private String channel_tag;
  private String email;
  private String guid;

  public CreatePushElement() {
  }

  public String getType() {
    return type;
  }

  public CreatePushElement setType(String type) {
    this.type = type;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public CreatePushElement setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getBody() {
    return body;
  }

  public CreatePushElement setBody(String body) {
    this.body = body;
    return this;
  }

  public String getUrl() {
    return url;
  }

  public CreatePushElement setUrl(String url) {
    this.url = url;
    return this;
  }

  public String getFile_name() {
    return file_name;
  }

  public CreatePushElement setFile_name(String file_name) {
    this.file_name = file_name;
    return this;
  }

  public String getFile_type() {
    return file_type;
  }

  public CreatePushElement setFile_type(String file_type) {
    this.file_type = file_type;
    return this;
  }

  public String getFile_url() {
    return file_url;
  }

  public CreatePushElement setFile_url(String file_url) {
    this.file_url = file_url;
    return this;
  }

  public String getSource_device_iden() {
    return source_device_iden;
  }

  public CreatePushElement setSource_device_iden(String source_device_iden) {
    this.source_device_iden = source_device_iden;
    return this;
  }

  public String getDevice_iden() {
    return device_iden;
  }

  public CreatePushElement setDevice_iden(String device_iden) {
    this.device_iden = device_iden;
    return this;
  }

  public String getClient_iden() {
    return client_iden;
  }

  public CreatePushElement setClient_iden(String client_iden) {
    this.client_iden = client_iden;
    return this;
  }

  public String getChannel_tag() {
    return channel_tag;
  }

  public CreatePushElement setChannel_tag(String channel_tag) {
    this.channel_tag = channel_tag;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public CreatePushElement setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getGuid() {
    return guid;
  }

  public CreatePushElement setGuid(String guid) {
    this.guid = guid;
    return this;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((body == null) ? 0 : body.hashCode());
    result = prime * result + ((channel_tag == null) ? 0 : channel_tag.hashCode());
    result = prime * result + ((client_iden == null) ? 0 : client_iden.hashCode());
    result = prime * result + ((device_iden == null) ? 0 : device_iden.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((file_name == null) ? 0 : file_name.hashCode());
    result = prime * result + ((file_type == null) ? 0 : file_type.hashCode());
    result = prime * result + ((file_url == null) ? 0 : file_url.hashCode());
    result = prime * result + ((guid == null) ? 0 : guid.hashCode());
    result = prime * result + ((source_device_iden == null) ? 0 : source_device_iden.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + ((type == null) ? 0 : type.hashCode());
    result = prime * result + ((url == null) ? 0 : url.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    CreatePushElement other = (CreatePushElement) obj;
    if (body == null) {
      if (other.body != null)
        return false;
    } else if (!body.equals(other.body))
      return false;
    if (channel_tag == null) {
      if (other.channel_tag != null)
        return false;
    } else if (!channel_tag.equals(other.channel_tag))
      return false;
    if (client_iden == null) {
      if (other.client_iden != null)
        return false;
    } else if (!client_iden.equals(other.client_iden))
      return false;
    if (device_iden == null) {
      if (other.device_iden != null)
        return false;
    } else if (!device_iden.equals(other.device_iden))
      return false;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (file_name == null) {
      if (other.file_name != null)
        return false;
    } else if (!file_name.equals(other.file_name))
      return false;
    if (file_type == null) {
      if (other.file_type != null)
        return false;
    } else if (!file_type.equals(other.file_type))
      return false;
    if (file_url == null) {
      if (other.file_url != null)
        return false;
    } else if (!file_url.equals(other.file_url))
      return false;
    if (guid == null) {
      if (other.guid != null)
        return false;
    } else if (!guid.equals(other.guid))
      return false;
    if (source_device_iden == null) {
      if (other.source_device_iden != null)
        return false;
    } else if (!source_device_iden.equals(other.source_device_iden))
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    } else if (!title.equals(other.title))
      return false;
    if (type == null) {
      if (other.type != null)
        return false;
    } else if (!type.equals(other.type))
      return false;
    if (url == null) {
      if (other.url != null)
        return false;
    } else if (!url.equals(other.url))
      return false;
    return true;
  }

}
