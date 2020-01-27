package co.adeshina.kafka.demo.consumer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {

    private int partition;
    private String content;
    private String senderName;

    public int getPartition() {
        return partition;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPartition(int partition) {
        this.partition = partition;
    }
}
