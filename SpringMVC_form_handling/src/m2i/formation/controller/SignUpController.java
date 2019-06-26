package m2i.formation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import m2i.formation.model.SignUpForm;

@Controller
public class SignUpController {

	/**
	 * Créer un objet signUpForm vide pour un formulaire vide
	 * 
	 * @return
	 */
	@ModelAttribute("signUpForm")
	public SignUpForm setSignUpForm() {
		return new SignUpForm();
	}

	/**
	 * Montrer le formulaire HTML dans le fichier signup-form.jsp
	 * 
	 * @return
	 */
	@GetMapping("/showSignUpForm")
	public String showForm() {
		return "signup-form";
	}

	/**
	 * Formulaire d'enregistrement signup-success.jsp
	 * 
	 * @param signUpForm
	 * @param model
	 * @return
	 */
	@PostMapping("/saveSignUpForm")
	public String saveUser(@ModelAttribute("signUpForm") SignUpForm signUpForm, Model model) {

		// Implement business logic to save user details into a database
		// .....

		System.out.println("FirstName : " + signUpForm.getFirstName());
		System.out.println("LastName : " + signUpForm.getLastName());
		System.out.println("Username : " + signUpForm.getUserName());
		System.out.println("Password : " + signUpForm.getPassword());
		System.out.println("Email : " + signUpForm.getEmail());

		model.addAttribute("message", "User SignUp successfully.");
		model.addAttribute("user", signUpForm);

		return "signup-success";
	}
}
