package co.grandcircus.capstone_6.controllers;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.grandcircus.capstone_6.Task;
import co.grandcircus.capstone_6.User;
import co.grandcircus.capstone_6.daos.TaskDao;
import co.grandcircus.capstone_6.daos.UserDao;



//Note: research more about how to pass values from one controller to another so that
//I can use separate controllers. (FlashAttributes? Redir? Look it up or ask.)



//To Do: 1. Format dates, 2. Create regex for the sign up form,
//3. double check some of the catchers for wrong input




@Controller
public class UserController {
	
	//figure out how to make it so it doesn't log out when I refresh
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserDao userRepo;
	
	@Autowired
	private TaskDao taskRepo;
	
	boolean loggedIn = false;
	String loginMessage = "Please enter your username and password.";
	String signUpMessage = "Please enter the following information.";
	String infoMessage = "Here is your user information.";
	String editMessage = "Edit your user info here.";
	
	
	
	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	//idk if I need some of these or not. I'll figure it out later.
	public UserDao getRepo() {
		return userRepo;
	}

	public void setRepo(UserDao repo) {
		this.userRepo = repo;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getMessage() {
		return loginMessage;
	}

	public void setMessage(String message) {
		this.loginMessage = message;
	}
	
	@RequestMapping("/modify-tasks")
	public String modifyTasks(Model model) {
		
		User user = (User)session.getAttribute("user");
		List<Task> userTasks = taskRepo.findAllByUser(user);
		
		
		infoMessage = "Here is your user information.";
		editMessage = "Edit your user info here.";
		
		//if not logged in, it will redirect
		if (user == null) {
			loggedIn = false;
			model.addAttribute("loggedin", loggedIn);
			
			return "redirect:/login";
		} else {
			
			loggedIn = true;
			model.addAttribute("user", user);
			model.addAttribute("loggedin", loggedIn);

			model.addAttribute("listsize", userTasks.size());
			model.addAttribute("tasks", userTasks);
			
			
			return "modify-tasks";
		}
		
		
	}
	
	@PostMapping("/edit/save")
	public String saveTask(
			@RequestParam(value="description") String description,
			@RequestParam("date") 
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
			@RequestParam(value="complete") int complete,
			//@RequestParam(value="taskid") Long taskId,
			@RequestParam(value="taskid") Long taskId,
			Model model
			) {
		
		User user = (User)session.getAttribute("user");
		List<Task> userTasks = taskRepo.findAllByUser(user);
		int iComplete = 2;
		
		for (Task task: userTasks) {
			if (task.getId() == taskId) {
				task.setUser(user);
				task.setDescription(description);
				task.setDate(date);
				
				task.setComplete(complete);
				
				taskRepo.save(task);
				session.setAttribute("task", task);
			}
		}
		
		//Converting obtained Date object to LocalDate object
	    //Instant instant = date.toInstant();
	    //ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
	    //LocalDate localDate = zone.toLocalDate();
		
		//I bet there's an easier way to do this part
		//Convert date to a string
		//SimpleDateFormat formatter = new SimpleDateFormat("MMddyyyy");  
		//String strDate = formatter.format(date);  
		
		//convert string to local date (but how did I do this in another project?)
		//LocalDate lDate = convertToLocalDate(date);
		//System.out.print(date);
		
		//Set new properties for task
		/*for (Task task: userTasks) {
			if (task.getId() == taskId) {
				task.setDescription(description);
				task.setDate(date);
				
				if (complete.contentEquals("true")) {
					task.setComplete(1);
				} else {
					task.setComplete(0);
				}
				
				taskRepo.save(task);
				
			}
		}*/
		
		return "redirect:/modify-tasks";
	}
	
	//Delete the darn task already
	@RequestMapping("/delete/{id}")
	public String deleteTask(
			@PathVariable("id") Long id) {
		taskRepo.deleteById(id);
		return "redirect:/modify-tasks";
	}
	
	
	
	@RequestMapping("/add") 
	public String addTask(
			
			
			Model model
			) {
		
		User user = (User)session.getAttribute("user");
		
		model.addAttribute("user", user);
		model.addAttribute("loggedin", loggedIn);
		
		if (loggedIn == true) {
			return "add";
		} else {
			return "redirect:/login";
		}
		
		
	}
	
	@PostMapping("/add/submit")
	public String addSubmit(
			@RequestParam("user") String username,
			@RequestParam("description") String description,
			@RequestParam("date") 
			@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
			Model model) {
		
		User user = userRepo.findByUsername(username);
		
		Task task = new Task(user, description, date);
		taskRepo.save(task);
		
		
		return("redirect:/modify-tasks");
	}
	
	//Review Tasks
	@RequestMapping("/review-tasks")
	public String reviewTasks (
			//@RequestParam(value="redirectAttributes")RedirectAttributes redirectAttributes,
			Model model) {
		
		User user = (User)session.getAttribute("user");
		List<Task> userTasks = taskRepo.findAllByUser(user);
		
		infoMessage = "Here is your user information.";
		editMessage = "Edit your user info here.";
		
		//if not logged in, it will redirect
		if (user == null) {
			loggedIn = false;
			model.addAttribute("loggedin", loggedIn);
			
			return "redirect:/login";
		} else {
			
			loggedIn = true;
			model.addAttribute("user", user);
			model.addAttribute("loggedin", loggedIn);

			model.addAttribute("listsize", userTasks.size());
			model.addAttribute("tasks", userTasks);
			
			
			return "review-tasks";
		}
		
		//HttpSession session = (HttpSession)redirectAttributes.getAttribute("session");
		//boolean loggedIn = (boolean)redirectAttributes.getAttribute("loggedIn");
		
		
		//redirectAttributes.addFlashAttribute("session", session);
		//redirectAttributes.addFlashAttribute("user", user);
		//redirectAttributes.addFlashAttribute("loggedIn", loggedIn);
		
	}
	
	@RequestMapping("/user-info")
	public String userSettings(Model model) {
		
		User user = (User)session.getAttribute("user");
		String hiddenPass = "";
		
		//redirect to login if not logged in
		if (user == null) {
			return "redirect:/login";
		}
		
		for (int i = 0; i < user.getPassword().length(); i++) {
			hiddenPass += "*";
		}
		
		
		model.addAttribute("user", user);
		model.addAttribute("loggedin", loggedIn);
		model.addAttribute("password", hiddenPass);
		model.addAttribute("message", infoMessage);
		
		return "user-info";
	}
	
	@RequestMapping("/user/edit")
	public String editUser(Model model) {
		
		User user = (User)session.getAttribute("user");
		
		
		//redirect to login if not logged in
		if (user == null) {
			return "redirect:/login";
		}
		
		model.addAttribute("user", user);
		model.addAttribute("loggedin", loggedIn);
		model.addAttribute("message", editMessage);
		
		return "edit";
	}
	
	//Edit an option
	@PostMapping("/user/edit/submit")
	public String edit(
			@RequestParam(value="userid") Long userId,
			@RequestParam(value="username") String username,
			@RequestParam(value="email") String email,
			@RequestParam(value="password1") String password1,
			@RequestParam(value="password2") String password2,
			@RequestParam(value="name") String name,
			Model model) {
		
		List<User> users = userRepo.findAll();
		User us = (User)session.getAttribute("user");
		User user = userRepo.findByUsername(us.getUsername()); //unnecessary steps - fix
		
		for (User u: users) {
			if (u.getUsername().equals(username) && u.getId() != user.getId()) {
				editMessage = "New username is unavailable. Please choose another.";
				model.addAttribute("user", user);
				model.addAttribute("loggedin", loggedIn);
				model.addAttribute("message", infoMessage);
				return "redirect:/user/edit";
			}
			
		}
		
		if (!password1.equals(password2)) {
			editMessage = "Passwords did not match. Please try again.";
			model.addAttribute("user", user);
			model.addAttribute("loggedin", loggedIn);
			model.addAttribute("message", editMessage);
			return "redirect:/user/edit";
		} else {
			//make it so the email has to match a regex too
			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(password1);
			user.setName(name);
			userRepo.save(user);
			session.setAttribute("user", user);
			loggedIn = true;
			infoMessage = "Information was successfully edited.";
			return "redirect:/user-info";
			
		}
	}

	//Login
	@RequestMapping("/login")
	public String signIn(
			Model model
			) {
		
		User user = (User)session.getAttribute("user");
		
		//if user isn't null, then add user
		//otherwise just add boolean to let page know there's no user yet
		//the comparison is an extra precaution to make sure boolean is set correctly
		if (user == null) {
			loggedIn = false;
		} else {
			loggedIn = true;
			model.addAttribute("user", user);
		}
		
		//add boolean to page
		model.addAttribute("loggedin", loggedIn);
		model.addAttribute("message", loginMessage);
		
		infoMessage = "Here is your user information.";
		editMessage = "Edit your user info here.";
		
		
		return "login";
	}
	
	@PostMapping("/login/submit")
	public String submit(
			@RequestParam("identity") String identity,
			@RequestParam String password,
			Model model,
			RedirectAttributes redir //not sure what this does? Pass to another method? useful
			) {
		
		User user;
		
		//see if user exists
		//find out if it's an email or username
		if (identity.contains("@")) {
			user = userRepo.findByEmail(identity);
		} else {
			user = userRepo.findByUsername(identity);
		}
		
		//1. user is found
		// - or -
		//2. password is incorrect
		
		if (user == null || !password.contentEquals(user.getPassword())) {
			loginMessage = "Your username, email, or password was incorrect.";
			model.addAttribute("message", loginMessage);
			loggedIn = false;
			return "redirect:/login";
		} else {
			loggedIn = true;
		}
		
		//add data to the session? not sure I understand this part fully yet
		session.setAttribute("user", user);
		
		//I don't think this is actually needed with the way I'm doing it
		//but it is a nifty thing
		redir.addFlashAttribute("message", "You are now logged in, " + user.getName() + ".");
		
		return "redirect:/review-tasks";
	}
	
	
	//Logout
	@RequestMapping("/logout")
	public String logout(RedirectAttributes redir) {
		
		loginMessage = "Please enter your username and password.";
		signUpMessage = "Please enter the following information.";
		infoMessage = "Here is your user information.";
		editMessage = "Edit your user info here.";
		
		//removes objects added to session
		
		session.invalidate();
		loggedIn = false;
		return "redirect:/login";
	}
	
	
	@RequestMapping("/sign-up")
	public String signUp(
			Model model
			) {
		
		model.addAttribute("loggedin", loggedIn);
		model.addAttribute("message", signUpMessage);
		
		return("sign-up");
	}
	
	@PostMapping("sign-up/submit")
	public String signUpSubmit(
			@RequestParam(value="username") String username,
			@RequestParam(value="email") String email,
			@RequestParam(value="password1") String password1,
			@RequestParam(value="password2") String password2,
			@RequestParam(value="name") String name,
			Model model) {
		
		List<User> users = userRepo.findAll();
		
		for (User u: users) {
			if (u.getUsername().equals(username)) {
				signUpMessage = "Username already exists. Please choose another.";
				model.addAttribute("loggedin", loggedIn);
				model.addAttribute("message", signUpMessage);
				return "redirect:/sign-up";
			}
			if (u.getEmail().equals(email)) {
				signUpMessage = "Email already exists. Please choose another.";
				model.addAttribute("loggedin", loggedIn);
				model.addAttribute("message", signUpMessage);
				return "redirect:/sign-up";
			}
			
		}
		
		if (!password1.equals(password2)) {
			signUpMessage = "Passwords did not match. Please try again.";
			model.addAttribute("loggedin", loggedIn);
			model.addAttribute("message", signUpMessage);
			return "redirect:/sign-up";
		} else {
			//make it so the email has to match a regex too
			User user = new User(username, email, password1, name);
			userRepo.save(user);
			session.setAttribute("user", user);
			loggedIn = true;
			infoMessage ="Sign up was successful!";
			return "redirect:/user-info";
			
		}
	}
		
	
	//Putting the header control here so I can access these variables
	@RequestMapping("partials/header") 
	public String header(
			//RedirectAttributes redirectAttributes,
			Model model
			) {
		User user = (User)session.getAttribute("user");
		 //let's try this
		if (user != null) {
			model.addAttribute("user", user);
		} 
			
			model.addAttribute("loggedin", loggedIn);
			//redirectAttributes.addFlashAttribute("user", user);
			//redirectAttributes.addFlashAttribute("user", user);
			//redirectAttributes.addFlashAttribute("loggedIn", loggedIn);
		return "header";
	}
	
	//not sure if I'll need this yet
	//display info from the session
	@RequestMapping("/session")
	public String showSession(Model model) {
		model.addAttribute("sessionID", session.getId());
		
		return "session-view";
	}
	
	//Stuff that I would have in a TaskController
	
	//return boolean based on integer
			public boolean getBoolByInt(int i) {
				if (i == 1) {
					return true;
				} else {
					return false;
				}
			}
			
			//return integer based on boolean
			public int getIntByBool(boolean b) {
				if (b == true) {
					return 1;
				} else {
					return 0;
				}
			}
			
	//there's probably an easier way
	public LocalDate convertToLocalDate(String sDate) {
		
		//yyyy-mm-dd
		
		String nums[] = sDate.split("-");
		
		
		
		int iYear = Integer.parseInt(nums[0]);
		int iMonth = Integer.parseInt(nums[1]);
		int iDay = Integer.parseInt(nums[2]);
		
		LocalDate localDate = LocalDate.of(iYear,iDay,iMonth);
		
		
	    return localDate;
	}
	
}
