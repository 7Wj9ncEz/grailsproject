package grailsproject

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

	private static final long serialVersionUID = 1

	transient springSecurityService


	boolean enabled = true
	boolean accountExpired = false
	boolean accountLocked = false
	boolean passwordExpired = false


    String username
    String password
    /*String firstName
    String lastName
    String email
    String confirmPassword*/


	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this)*.role
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	static transients = ['springSecurityService']

	static constraints = {
		password blank: false, password: true
		username blank: false, unique: true
	}

    /*static constraints = {
        firstName blank:false
        lastName  blank:false
        email blank:false, unique:true, email:true
        username  blank:false, size:5..20, matches:/[\S]+/, unique:true
        password  blank:false, size:8..15, matches:/[\S]+/, validator:{ val, obj ->
            if (obj.password != obj.confirmPassword)
                return 'user.password.dontmatch'
        }
    }*/

	static mapping = {
		password column: '`password`'
	}
}
