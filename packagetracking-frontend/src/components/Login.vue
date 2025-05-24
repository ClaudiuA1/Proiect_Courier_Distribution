<template>
  <div class="auth-container">
    <video autoplay muted loop playsinline class="background-video">
      <source src="backgroundgif.mp4" type="video/mp4" />
      Your browser does not support the video tag.
    </video>

    <div class="form-container">
      <h1 v-if="isLoginMode">Login</h1>
      <form @submit.prevent="handleSubmit">
        <div class="form-group">
          <label for="email">Email:</label>
          <input type="email" id="email" v-model="email" required />
        </div>
        <div class="form-group">
          <label for="password">Password:</label>
          <input type="password" id="password" v-model="password" required />
        </div>

        <button type="submit" @click="attemptLogin">Login</button>
      </form>

    </div>
  </div>
</template>


<script>
import axios from 'axios';
export default {
  data() {
    return {
      isLoginMode: true,
      email: '',
      password: '',
      confirmPassword: '',
    };
  },
  methods: {

    async attemptLogin() {
      try {
      const response = await axios.post('http://localhost:8080/auth/login', {
        email: this.email,
        password: this.password,
      });

      if (response.data.success) {
        // Save token or handle successful login
        localStorage.setItem('token', response.data.token);
        alert('Login successful!');
        // Redirect to another page if needed
        
        this.$router.push('/dashboard');
      } else {
        alert('Invalid email or password.');
      }
      } catch (error) {
      console.error("Error during login:", error);
      alert('An error occurred during login. Please try again.');
      }
    }
  },

};
</script>

<style scoped>
html,body
{
    width: 100%;
    height: 100%;
    margin: 0px;
    padding: 0px;
    overflow-x: hidden; 
}
.auth-container {
  position: relative;
  height: 100vh;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
}

.background-video {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit:cover;
  z-index: -1;
}

.form-container {
  z-index: 1;
  background: rgba(255, 255, 255, 0.9);
  /* ușor transparent pentru a vedea fundalul */
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
  width: 300px;
}


h1 {
  text-align: center;
  margin-bottom: 1rem;
}

.form-group {
  margin-bottom: 1rem;
}

label {
  display: block;
  margin-bottom: 0.5rem;
}

input {
  width: 100%;
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  width: 100%;
  padding: 0.5rem;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}

p {
  text-align: center;
  margin-top: 1rem;
}

p button {
  background: none;
  border: none;
  color: #007bff;
  cursor: pointer;
  text-decoration: underline;
}

p button:hover {
  color: #0056b3;
}
</style>