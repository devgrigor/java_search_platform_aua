package crawler;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import org.springframework.data.redis.core.RedisHash;


@Entity // This tells Hibernate to make a table out of this class
@Table(name = "meta_data")

public class Metadata {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private String title;

    private String description;

    private String url;

	public Metadata(String title, String description, String url){
		this.title = title;
		this.description = description;
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }


}