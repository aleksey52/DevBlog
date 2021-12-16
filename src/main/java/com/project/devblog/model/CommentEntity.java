package com.project.devblog.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@DynamicInsert
@DynamicUpdate
@Data
@ToString(exclude = {"article", "author", "receiver"})
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "comments")
public class CommentEntity extends AuditableBaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String message;
    Boolean enabled;
    @Column(name = "deletion_date")
    LocalDateTime deletionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    ArticleEntity article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    UserEntity author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    UserEntity receiver;

    /*-----------------------------------FOR_MANY_TO_ONE--------------------------------*/
    public void setArticle(ArticleEntity article) {
        this.article = article;
        article.getComments().add(this);
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
        author.getSelfComments().add(this);
    }

    public void setReceiver(UserEntity receiver) {
        this.receiver = receiver;
        receiver.getReceivedComments().add(this);
    }
}