package classes;

public enum RolesEnum {
	Moderator(      new Privileges(true, true,true,true)),
	Administrator(  new Privileges(true, true,true,false)),
	Anonymous(      new Privileges(false, false,false,false)),
	User(           new Privileges(true, true,false,false));

	private Privileges privileges;

	public Privileges getPrivilages() {
		return privileges;
	}

	/**
	 * 
	 * @param privilages
	 */
	RolesEnum(Privileges privilages) {
		this.privileges = privilages;
	}

}