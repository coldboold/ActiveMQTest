import java.io.Serializable;

public class Student implements Serializable {

	public Student(String name, int age, String telphone, String address) {
		this.name = name;
		this.age = age;
		this.telphone = telphone;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String toString() {
		return "the name is:" + name + " age is:" + age + " telphone is:" + telphone + " address is:" + address;
	}

	private String name;
	private String address;
	private int age;
	private String telphone;
}
