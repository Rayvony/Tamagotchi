package com.tallerwebi.controller;

            import com.tallerwebi.models.User;
            import com.tallerwebi.service.UserService;
            import org.springframework.beans.factory.annotation.Autowired;
            import org.springframework.http.ResponseEntity;
            import org.springframework.web.bind.annotation.*;
            import org.springframework.web.servlet.ModelAndView;

            import javax.servlet.http.HttpServletRequest;
            import javax.servlet.http.HttpServletResponse;

            @RestController
            @RequestMapping("/api/users")
            public class UserController {

                @Autowired
                private UserService userService;

                @PostMapping("/register")
                public ResponseEntity<User> registerUser(@RequestParam String username, @RequestParam String password) {
                    try {
                        User user = userService.registerUser(username, password);
                        return ResponseEntity.ok(user);
                    } catch (RuntimeException e) {
                        return ResponseEntity.badRequest().body(null);
                    }
                }

                @GetMapping("/login")
                public ModelAndView irALogin() {
                    return new ModelAndView("login");
                }

                @PostMapping("/login")
                public ResponseEntity<User> loginUser(@RequestParam String username, @RequestParam String password) {
                    try {
                        User user = userService.loginUser(username, password);
                        return ResponseEntity.ok(user);
                    } catch (RuntimeException e) {
                        return ResponseEntity.badRequest().body(null);
                    }
                }

                @PostMapping("/logout")
                public ResponseEntity<String> logoutUser(HttpServletRequest request, HttpServletResponse response) {
                    try {
                        request.getSession().invalidate();
                        return ResponseEntity.ok("Sesión cerrada correctamente");
                    } catch (Exception e) {
                        return ResponseEntity.badRequest().body("Error al cerrar la sesión");
                    }
                }

                @GetMapping("/validate")
                public ResponseEntity<String> validateUser(@RequestParam String token) {
                    try {
                        userService.validateUser(token);
                        return ResponseEntity.ok("Usuario validado correctamente");
                    } catch (RuntimeException e) {
                        return ResponseEntity.badRequest().body(e.getMessage());
                    }
                }
            }