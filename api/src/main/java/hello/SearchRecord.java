package hello;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.Column;

@Entity // This tells Hibernate to make a table out of this class
 public class SearchRecord implements Serializable{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
	private String keyword;
 	private int popularity;

 	@Column(name = "keyword")
 	public String getKeyword(){
 		return this.keyword;
 	}

 	public int getPopularity(){
 		return this.popularity;
 	}

 	public Integer getId(){
 		return this.id;
 	}


 	public void setKeyword(String keyword){
 		this.keyword = keyword;
 	}

 	public void setPopularity(int popularity){
 		this.popularity = popularity;
 	}

 	public void setId(Integer id){
 		this.id = id;
 	}



 }