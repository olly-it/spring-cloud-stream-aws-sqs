package it.olly.springcloudstream.awssqs.model;

public class BMessage {
    private Long id;
    private String message;
    private String sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BMessage [id=");
        builder.append(id);
        builder.append(", message=");
        builder.append(message);
        builder.append(", sex=");
        builder.append(sex);
        builder.append("]");
        return builder.toString();
    }

}
