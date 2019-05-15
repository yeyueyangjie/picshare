package com.yeyue.pictureshare.model;

public class CommentEntity {
    /**
     * 评论id
     */
    private String commentId;
    /**
     * 被评论的pic
     */
    private String picShare;
    /**
     * 父评论id
     */
    private String fatherComment;
    /**
     * 评论者id
     */
    private String commented;
    /**
     * 评论内容
     */
    private String summary;
    /**
     * 评论时间
     */
    private String created;
    /**
     * 删除标志
     */
    private String del;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getPicShare() {
        return picShare;
    }

    public void setPicShare(String picShare) {
        this.picShare = picShare;
    }

    public String getFatherComment() {
        return fatherComment;
    }

    public void setFatherComment(String fatherComment) {
        this.fatherComment = fatherComment;
    }

    public String getCommented() {
        return commented;
    }

    public void setCommented(String commented) {
        this.commented = commented;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDel() {
        return del;
    }

    public void setDel(String del) {
        this.del = del;
    }
}
