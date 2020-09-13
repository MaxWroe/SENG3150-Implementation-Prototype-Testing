
package group3.seng3150.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "Review")
public class Review {

    @Id
    @Column(name = "ReviewID")
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String reviewID;

    @Column(name = "UserID")
    @Basic(optional = false)
    private String userID;

    @Column(name = "ReviewDate")
    @Basic(optional = false)
    private Date reviewDate;

    @Column(name = "Comment")
    private String comment;

    @Column(name = "Name")
    private String name;

    //1 to 5
    @Column(name = "Rating")
    @Basic(optional = false)
    private int rating;

    //0 for review of a flight, 1 for airports, 2 for review of website
    @Column(name = "reviewType")
    @Basic(optional = false)
    private int reviewType;


    public Review() {
    }

    public String getReviewID() {
        return reviewID;
    }

    public void setReviewID(String reviewID) {
        this.reviewID = reviewID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getReviewType() {
        return reviewType;
    }

    public void setReviewType(int reviewType) {
        this.reviewType = reviewType;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewID='" + reviewID + '\'' +
                ", userID='" + userID + '\'' +
                ", reviewDate=" + reviewDate +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                ", reviewType=" + reviewType +
                '}';
    }
}
