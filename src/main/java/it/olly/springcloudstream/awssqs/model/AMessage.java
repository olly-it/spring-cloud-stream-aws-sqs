package it.olly.springcloudstream.awssqs.model;

public class AMessage {
    private Long id;
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AMessage [id=");
        builder.append(id);
        builder.append(", text=");
        builder.append(text);
        builder.append("]");
        return builder.toString();
    }

}
