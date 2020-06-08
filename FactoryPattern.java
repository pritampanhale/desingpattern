
public class FactoryPattern {

	public static void main(String[] args) {
		User user = new User("TestUserName", "1234 1234 1222", "BADPAN");
		AbstracValidator nameValidator = ValidatorFactory.getValidator(Validator.NameValidator);
		nameValidator.validate(user);
		
		AbstracValidator adharValidator = ValidatorFactory.getValidator(Validator.AdharValidator);
		adharValidator.validate(user);
		
		AbstracValidator panValidator = ValidatorFactory.getValidator(Validator.PanValidator);
		panValidator.validate(user);
	}

}

class ValidatorFactory {

	public static AbstracValidator getValidator(Validator validator) {
		switch (validator) {
		case NameValidator:
			return new NameValidator();
		case AdharValidator:
			return new AdharValidator();
		case PanValidator:
			return new PanValidator();
		default:
			return new AbstracValidator();
		}
	}
}

class AbstracValidator {
	public void validate(User user) {
		System.out.println("Default Method");
	}
}

class NameValidator extends AbstracValidator {
	public void validate(User user) {
		if (user.getName().length() < 10) {
			System.out.println("Name should be greater than 10 letters");
		} else {
			System.out.println("Name is correct");
		}
	}
}

class AdharValidator extends AbstracValidator {
	public void validate(User user) {
		if (user.getAdhar().length() < 12) {
			System.out.println("Adhar should be greater than 12 Digits");
		} else {
			System.out.println("Adhar is correct");
		}
	}
}

class PanValidator extends AbstracValidator {
	public void validate(User user) {
		if (user.getPan().length() < 10) {
			System.out.println("PAN should be greater than 8 letters");
		} else {
			System.out.println("PAN is correct");
		}
	}
}

class User {

	private String name;
	private String adhar;
	private String pan;

	User(String name, String adhar, String pan) {
		this.name = name;
		this.adhar = adhar;
		this.pan = pan;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdhar() {
		return adhar;
	}

	public void setAdhar(String adhar) {
		this.adhar = adhar;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

}

enum Validator {
	NameValidator, AdharValidator, PanValidator
}
