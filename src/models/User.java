package models;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
public class User {

	private int id;
	private String title;

	@Pattern(regexp = "[a-z,A-Z]{2}[a-z,A-Z]*",
			message="First Name can contain only Alphabets and its lenght must be at least 2 chars.")
	@NotEmpty(message="First Name can't be empty.")
	private String firstName;
	@Pattern(regexp = "[a-z,A-Z]{2}[a-z,A-Z]*",
			message="Last Name can contain only Alphabets and its lenght must be at least 2 chars.")
	@NotEmpty(message="List Name can't be empty.")
	private String lastName;
	@NotEmpty(message="MailId can't be empty.")
	@Email(message="MailId should be in proper fromat.")
	private String email;
	@NotEmpty(message="Password can't be empty.")
	@Length(min=4,message="Password lenght must be atleast 4 chars.")
	private String password;
	private String city,profile,imageUrl,mobileNo;
	private int expertise,court,userType;
	
	
	//possible values of expertise
	public static final int civil=1;
	public static final int criminal=2;
	public static final int company=3;
	public static final int divorce=4;
	public static final int juvenile=5;
	
	//possible values of courts
	public static final int session=1;
	public static final int high=2;
	public static final int supreme=3;
	public static final int consumer=4;
	public static final int juv=5;
	
	//possible values of userType
		public static final int professional=1;
		public static final int client=2;
		public static final int admin=3;
	
		public String getDisplayCourt()
		{
			if(court==1)
				return "Session Court";
			else if(court==2)
				return "High Court";
			else if(court==3)
				return "Supreme Court";
			else if(court==4)
				return "Consumer Court";
			else if(court==5)
				return "Juvenile Court";
			
			else
				return "";
		}	
		
		
	public String getDisplayExpertise()
	{
		if(expertise==1)
			return "Civil Cases";
		else if(expertise==2)
			return "Criminal Cases";
		else if(expertise==3)
			return "Company Cases";
		else if(expertise==4)
			return "Divorce Cases";
		else if(expertise==5)
			return "Consumer Cases";
		else if(expertise==6)
			return "Juvenile Cases";
		else
			return "";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city=city;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public int getExpertise() {
		return expertise;
	}
	public void setExpertise(int expertise) {
		this.expertise = expertise;
	}
	public int getCourt() {
		return court;
	}
	public void setCourt(int court) {
		this.court = court;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	
	
	
}
