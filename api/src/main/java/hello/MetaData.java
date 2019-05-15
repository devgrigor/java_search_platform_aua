package hello;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.Column;

@Entity // This tells Hibernate to make a table out of this class
 public class MetaData implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	private String title;
    private String description;
    private String url;

 	@Column(name = "title")
 	public String getTitle(){
 		return this.title;
 	}

 	public String getDescription(){
 		return this.description;
    }
     
     public String getUrl(){
        return this.url;
    }

 	public Integer getId(){
 		return this.id;
 	}

 	public void setId(Integer id){
 		this.id = id;
 	}



 }