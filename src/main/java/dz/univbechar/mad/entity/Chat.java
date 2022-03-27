package dz.univbechar.mad.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Objects;
import java.util.Set;
    
@Entity
public class Chat {
    @Id
    private long id;

    @OneToMany
    private Set<User> user;

    @OneToMany
    private Set<Message> messages;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Chat update(Chat chat){
        if(chat.user != null) setUser(chat.user);
        if(chat.messages != null) setMessages(chat.messages);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return id == chat.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
