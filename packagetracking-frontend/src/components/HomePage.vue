<template>
  <div class="font-sans bg-gray-50">
    <!-- Navigation -->
    <nav class="gradient-bg text-white shadow-lg">
      <div class="container mx-auto px-4 py-3 flex justify-between items-center">
        <div class="flex items-center space-x-2">
          <i class="fas fa-box-open text-2xl"></i>
          <span class="text-xl font-bold">OnlyCourier</span>
        </div>
        <!-- Desktop menu -->
        <div class="hidden md:flex items-center space-x-6">
          <a href="#" @click.prevent="trySelect('HomeContent')" :class="{ 'underline': activeTab === 'HomeContent' }"
            class="hover:text-purple-200 transition">Home</a>
          <a href="#" @click.prevent="trySelect('AllPackages')" :class="{ 'underline': activeTab === 'AllPackages' }"
            class="hover:text-purple-200 transition">All Packages</a>
          <a href="#" @click.prevent="trySelect('Assign')" :class="{ 'underline': activeTab === 'Assign' }"
            class="hover:text-purple-200 transition">Take Package</a>
          <a href="#" @click.prevent="trySelect('MyPackages')" :class="{ 'underline': activeTab === 'MyPackages' }"
            class="hover:text-purple-200 transition">My Packages</a>

          <!-- Login / Logout -->
          <button v-if="!loggedIn" @click="openModal('login')"
            class="bg-white text-purple-700 px-4 py-2 rounded-full font-medium hover:bg-purple-100 transition">
            Login
          </button>
          <button v-else @click="logout"
            class="bg-white text-red-500 px-4 py-2 rounded-full font-medium hover:bg-red-100 transition">
            Logout
          </button>
        </div>
      </div>
    </nav>

    <!-- Hero Section -->


    <!-- Dynamic content -->
    <component :is="activeTab" class="mt-6" />

    <!-- Login / Sign-up Modal -->
    <div class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4 z-50 login-modal"
      :class="{ active: modalActive }" @click.self="closeModal">
      <div class="bg-white rounded-xl shadow-2xl w-full max-w-md overflow-hidden">
        <div class="gradient-bg text-white p-6 flex justify-between items-center">
          <h3 class="text-xl font-bold">
            <!-- Dacă așteptăm OTP, arătăm alt titlu -->
            {{ mode === 'login'
              ? (awaitingOtp ? 'Enter One-Time Password' : 'Welcome Back')
              : 'Create Account' }}
          </h3>
          <button @click="closeModal" class="text-white hover:text-purple-200 transition">
            <i class="fas fa-times"></i>
          </button>
        </div>
        <div class="p-6">
          <!-- PASUL 1: Credențiale -->
          <form v-if="mode === 'login' && !awaitingOtp" @submit.prevent="handleLogin">
            <div class="mb-6 relative">
              <input v-model="email" type="email" id="email" required
                class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-purple-500 placeholder-transparent"
                placeholder="Your email" />
              <label for="email" class="floating-label">Email Address</label>
            </div>
            <div class="mb-6 relative">
              <input v-model="password" type="password" id="password" required
                class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-purple-500 placeholder-transparent"
                placeholder="Your password" />
              <label for="password" class="floating-label">Password</label>
            </div>
            <button type="submit"
              class="w-full gradient-bg text-white py-3 rounded-lg font-bold hover:bg-purple-700 transition mb-4">
              Sign In
            </button>
            <p class="text-center text-sm text-gray-600">
              Don't have an account?
              <button type="button" class="text-purple-700 font-medium hover:text-purple-500" @click="mode = 'signup'">
                Sign up
              </button>
            </p>
          </form>

          <!-- PASUL 2: OTP -->
          <form v-else-if="mode === 'login' && awaitingOtp" @submit.prevent="verifyOtp">
            <div class="mb-6 relative">
              <input v-model="enteredOtp" type="text" id="otp" required
                class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-purple-500"
                placeholder="Enter OTP" />
            </div>
            <button type="submit"
              class="w-full gradient-bg text-white py-3 rounded-lg font-bold hover:bg-purple-700 transition mb-4">
              Verify OTP
            </button>
          </form>
          <!-- SIGN UP FORM -->
          <form v-else @submit.prevent="handleSignUp">
            <div class="mb-6">
              <input v-model="newUser.name" type="text" id="name" required
                class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-purple-500"
                placeholder="Full Name" />
            </div>
            <div class="mb-6 relative">
              <input v-model="newUser.email" type="email" id="signup-email" required
                class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-purple-500 placeholder-transparent"
                placeholder="Email" />
              <label for="signup-email" class="floating-label">Email Address</label>
            </div>
            <div class="mb-6 relative">
              <input v-model="newUser.password" type="password" id="signup-password" required
                class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-purple-500 placeholder-transparent"
                placeholder="Password" />
              <label for="signup-password" class="floating-label">Password</label>
            </div>
            <div class="mb-6 relative">
              <input v-model="newUser.confirmPassword" type="password" id="confirm-password" required
                class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-purple-500 placeholder-transparent"
                placeholder="Confirm Password" />
              <label for="confirm-password" class="floating-label">Confirm Password</label>
            </div>
            <button type="submit"
              class="w-full gradient-bg text-white py-3 rounded-lg font-bold hover:bg-purple-700 transition mb-4">
              Create Account
            </button>
            <p class="text-center text-sm text-gray-600">
              Already have an account?
              <button type="button" class="text-purple-700 font-medium hover:text-purple-500" @click="mode = 'login'">
                Sign in
              </button>
            </p>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import HomeContent from './HomeContent.vue'
import AllPackages from './PackagesView.vue'
import Assign from './AssignCourier.vue'
import MyPackages from './YourPackages.vue'

export default {
  name: 'HomePage',
  components: {
    HomeContent,
    AllPackages,
    Assign,
    MyPackages
  },
  data() {
    return {
      mobileMenuOpen: false,
      modalActive: false,
      mode: 'login',
      email: '',
      password: '',
      newUser: { name: '', email: '', password: '', confirmPassword: '' },
      activeTab: 'HomeContent',
      awaitingOtp: false,
      enteredOtp: '',
      loggedIn: false

    }
  },
  methods: {
    trySelect(tabName) {
      if (!this.loggedIn && tabName !== 'HomeContent') {
        this.openModal('login')
      } else {
        this.activeTab = tabName
      }
      this.mobileMenuOpen = false
    },
    openModal(m) {
      this.mode = m
      this.modalActive = true
      document.body.style.overflow = 'hidden'
    },
    closeModal() {
      this.modalActive = false
      document.body.style.overflow = ''
    },
    async handleLogin() {
      try {
        // 1) login username/password
        const creds = new URLSearchParams()
        creds.append('username', this.email)
        creds.append('password', this.password)
        const res = await axios.post('/courier/login', creds)

        if (res.status === 200) {
          // 2) trimitem OTP ca form-urlencoded
          const otpForm = new URLSearchParams()
          otpForm.append('email', this.email)
          const otpRes = await axios.post('/auth/send-otp', otpForm)

          if (otpRes.status === 200) {
            this.awaitingOtp = true
            alert('OTP sent to your email!')
          } else {
            alert('Failed to send OTP!')
          }
        }
      } catch (err) {
        console.error('LOGIN ERROR', err)
        alert('Autentificare nereușită!')
      }
    },

  async verifyOtp() {
  try {
    const params = new URLSearchParams();
    params.append('email', this.email);
    params.append('password', this.password);
    params.append('otp', this.enteredOtp);

    const verifyRes = await axios.post('/auth/verify-otp', params);
    console.log('VERIFY RES DATA', verifyRes.data);

    const { id, email, message } = verifyRes.data;
    console.log('Parsed id, email, message:', id, email, message);

    if (id != null) {
      localStorage.setItem('courierId', id.toString());
      localStorage.setItem('courierEmail', email);

      this.loggedIn = true;
      this.awaitingOtp = false;
      this.closeModal();
      this.activeTab = 'AllPackages';
    } else {
      alert(message || 'OTP invalid!');
    }
  } catch (err) {
    console.error('VERIFY OTP ERROR', err);
    alert('OTP invalid!');
  }
},



    async handleSignUp() {
      if (this.newUser.password !== this.newUser.confirmPassword) {
        alert("Passwords don't match!")
        return
      }
      try {
        const payload = {
          name: this.newUser.name,
          email: this.newUser.email,
          password: this.newUser.password,
          manager_id: null
        }
        const res = await axios.post('http://localhost:8080/courier/create', payload)
        alert(`Account created: ${res.data.email}`)
        this.mode = 'login'
      } catch (err) {
        console.error(err)
        alert('Error creating account: ' + (err.response?.data || err.message))
      }
    },

    async logout() {
      console.log('🔴 logout button clicked')
      try {
        // Asigură-te că trimite cookie-ul JSESSIONID
        const res = await axios.post(
          '/courier/logout',
          {},
          { withCredentials: true }
        )
        console.log('Logout response:', res)
        // Ştergem token-ul local şi actualizăm starea
        localStorage.removeItem('token')
        this.loggedIn = false
        this.activeTab = 'HomeContent'
      } catch (err) {
        console.error('Logout error:', err)
      }

      // …restul metodelor…
    }



  }
}
</script>

<style>
.gradient-bg {
  background: linear-gradient(135deg, #7e22ce 0%, #9333ea 50%, #a855f7 100%);
}

.login-modal {
  transition: all 0.3s ease;
  opacity: 0;
  visibility: hidden;
}

.login-modal.active {
  opacity: 1;
  visibility: visible;
}

.floating-label {
  position: absolute;
  pointer-events: none;
  left: 12px;
  top: 12px;
  transition: 0.2s ease all;
  color: #9ca3af;
}

input:focus~.floating-label,
input:not(:placeholder-shown)~.floating-label {
  top: -8px;
  left: 10px;
  font-size: 12px;
  background-color: white;
  padding: 0 4px;
  color: #7e22ce;
}
</style>
