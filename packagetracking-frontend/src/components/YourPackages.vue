<template>
  <div class="bg-gray-100 min-h-screen">
    <div class="container mx-auto px-4 py-8">
      <!-- Header -->
      <header class="gradient-bg rounded-xl shadow-lg mb-8 p-6 text-white">
        <div class="flex flex-col md:flex-row md:items-center justify-between gap-4">
          <h2 class="text-2xl md:text-3xl font-bold">My Assigned Packages</h2>
          <div class="flex items-center space-x-2">
            <button
              @click="refreshData"
              :disabled="loading"
              class="bg-white text-purple-700 hover:bg-purple-50 px-6 py-2 rounded-lg font-medium flex items-center space-x-2 transition-colors duration-200"
            >
              <i :class="loading ? 'fas fa-spinner animate-spin' : 'fas fa-sync-alt'"></i>
              <span>{{ loading ? 'Refreshing...' : 'Refresh' }}</span>
            </button>
            <button
              @click="downloadPdf"
              :disabled="loading"
              class="bg-green-600 hover:bg-green-700 text-white px-6 py-2 rounded-lg font-medium flex items-center space-x-2 transition-colors duration-200"
            >
              <i class="fas fa-file-download"></i>
              <span>Download PDF</span>
            </button>
          </div>
        </div>
      </header>

      <!-- Table -->
      <main class="bg-white rounded-xl shadow-md overflow-hidden">
        <!-- Header Row -->
        <div class="hidden md:grid grid-cols-12 bg-gray-50 p-4 border-b border-gray-200 font-semibold text-gray-700">
          <div class="col-span-2">Tracking ID</div>
          <div class="col-span-3">Destination</div>
          <div class="col-span-2">Last Update</div>
          <div class="col-span-2">Status</div>
          <div class="col-span-1">Pay on delivery</div>
          <div class="col-span-2 text-right">Actions</div>
        </div>

        <!-- Rows -->
        <div class="divide-y divide-gray-200">
          <!-- Loading -->
          <div v-if="loading" class="text-center p-8 text-gray-500">
            <i class="fas fa-spinner animate-spin text-2xl mb-2"></i>
            <p>Loading your packages...</p>
          </div>

          <!-- Empty -->
          <div v-else-if="packages.length === 0" class="p-8 text-center text-gray-500">
            <p>No packages assigned to you.</p>
          </div>

          <!-- Data -->
          <div v-else>
            <div
              v-for="pkg in packages"
              :key="pkg.id"
              class="hidden md:grid grid-cols-12 items-center p-4 hover:bg-gray-50"
            >
              <div class="col-span-2 font-medium text-gray-800">{{ pkg.id }}</div>
              <div class="col-span-3 text-gray-600 truncate" :title="pkg.deliveryAddress">
                {{ pkg.deliveryAddress }}
              </div>
              <div class="col-span-2 text-gray-500">{{ formatDate(pkg.createdOn) }}</div>
              <div class="col-span-2">
                <span :class="statusClass(pkg.status) + ' text-white px-3 py-1 rounded-full text-sm'">
                  {{ pkg.status }}
                </span>
              </div>
              <div class="col-span-1 text-gray-800 font-medium">{{ pkg.payOnDelivery ? 'Yes' : 'No' }}</div>
              <div class="col-span-2 text-right">
                <button @click="openDetails(pkg)" class="text-purple-600 hover:text-purple-800">
                  <i class="fas fa-eye"></i>
                </button>
              </div>
            </div>
          </div>
        </div>
      </main>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'AssignedPackagesView',
  data() {
    return {
      packages: [],
      loading: false
    }
  },
  methods: {
    async refreshData() {
      this.loading = true
      await this.fetchAssignedPackages()
      this.loading = false
    },
    async fetchAssignedPackages() {
      try {
        const courierId = localStorage.getItem('courierId')
        const { data } = await axios.get('/package/CourierPackage', { params: { id: courierId } })
        this.packages = data
      } catch (e) {
        console.error('Error fetching assigned packages:', e)
      }
    },
    openDetails(pkg) {
      this.$emit('show-package-details', pkg)
    },
    async downloadPdf() {
      try {
        this.loading = true
        const courierId = localStorage.getItem('courierId')
        const res = await axios.get(
          `/courier/${courierId}/packages/pdf`,
          { responseType: 'blob', withCredentials: true }
        )
        const blob = new Blob([res.data], { type: 'application/pdf' })
        const url = window.URL.createObjectURL(blob)
        const a = document.createElement('a')
        a.href = url
        a.download = 'my-packages.pdf'
        document.body.appendChild(a)
        a.click()
        document.body.removeChild(a)
        window.URL.revokeObjectURL(url)
        alert('The PDF password was sent to your email.')
      } catch (err) {
        console.error('PDF download error', err)
        alert('Could not generate PDF.')
      } finally {
        this.loading = false
      }
    },
    statusClass(status) {
      switch (status.toLowerCase()) {
        case 'delivered':   return 'status-delivered'
        case 'in transit':  return 'status-in-transit'
        case 'pending':     return 'status-pending'
        case 'failed':      return 'status-failed'
        default:            return 'bg-gray-500'
      }
    },
    formatDate(date) {
      return new Date(date).toLocaleDateString('en-US', {
        year: 'numeric', month: 'short', day: 'numeric', hour: '2-digit', minute: '2-digit'
      })
    }
  },
  mounted() {
    this.refreshData()
  }
}
</script>

<style scoped>
.gradient-bg {
  background: linear-gradient(135deg, #6b21a8 0%, #9333ea 100%);
}
.status-delivered   { background-color: #10b981; }
.status-in-transit  { background-color: #f59e0b; }
.status-pending     { background-color: #64748b; }
.status-failed      { background-color: #ef4444; }
</style>
