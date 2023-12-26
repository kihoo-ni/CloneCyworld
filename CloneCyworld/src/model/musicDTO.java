package model;

public class musicDTO {

	private int m_no;
	private String id;
	private String artist;
	private String title;
	private String created;
	private String youtube_id;
	private boolean picked;
	
	public int getM_no() {
		return m_no;
	}
	public void setM_no(int m_no) {
		this.m_no = m_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getYoutube_id() {
		return youtube_id;
	}
	public void setYoutube_id(String youtube_id) {
		this.youtube_id = youtube_id;
	}
	public boolean isPicked() {
		return picked;
	}
	public void setPicked(boolean picked) {
		this.picked = picked;
	}
	
	
}
