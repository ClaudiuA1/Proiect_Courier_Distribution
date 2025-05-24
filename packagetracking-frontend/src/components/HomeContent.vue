<template>
  <section class="gradient-bg text-white py-16 md:py-24">
      <div class="container mx-auto px-4 flex flex-col md:flex-row items-center">
        <div class="md:w-1/2 mb-10 md:mb-0">
          <h1 class="text-4xl md:text-5xl font-bold mb-4">Deliver with Confidence</h1>
          <p class="text-xl mb-6">An app made by Couriers, only for couriers</p>
          <div class="flex flex-col sm:flex-row space-y-3 sm:space-y-0 sm:space-x-4">
            <button class="bg-white text-purple-700 px-6 py-3 rounded-full font-bold hover:bg-purple-100 transition">Get
              Started</button>
            <button
              class="border-2 border-white text-white px-6 py-3 rounded-full font-bold hover:bg-white hover:text-purple-700 transition">Learn
              More</button>
          </div>
        </div>
        <div class="md:w-1/2 flex justify-center">
          <div class="relative w-full max-w-md">
            <div class="absolute -top-6 -left-6 w-32 h-32 bg-purple-300 rounded-full opacity-20"></div>
            <div class="absolute -bottom-6 -right-6 w-32 h-32 bg-purple-400 rounded-full opacity-20"></div>
            <div class="relative bg-white rounded-xl shadow-2xl p-6">
              <div class="flex justify-between items-center mb-4">
                <h3 class="text-purple-900 font-bold">Package #OC-78945</h3>
                <span class="bg-purple-100 text-purple-800 text-xs px-2 py-1 rounded">In Transit</span>
              </div>
              <div class="flex items-center space-x-4 mb-6">
                <div class="bg-purple-100 p-3 rounded-full">
                  <i class="fas fa-box text-purple-700"></i>
                </div>
                <div>
                  <p class="text-gray-500 text-sm">Recipient</p>
                  <p class="text-gray-900 font-medium">John Smith</p>
                </div>
              </div>
              <div class="flex items-center space-x-4 mb-6">
                <div class="bg-purple-100 p-3 rounded-full">
                  <i class="fas fa-map-marker-alt text-purple-700"></i>
                </div>
                <div>
                  <p class="text-gray-500 text-sm">Destination</p>
                  <p class="text-gray-900 font-medium">123 Main St, New York</p>
                </div>
              </div>
              <div class="flex justify-between items-center">
                <div>
                  <p class="text-gray-500 text-sm">Estimated Delivery</p>
                  <p class="text-gray-900 font-medium">Today, 3:00 PM</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>


</template>
<style scoped>.gradient-bg {
  background: linear-gradient(135deg, #7e22ce 0%, #9333ea 50%, #a855f7 100%);
}
.floating-label {
  position: absolute;
  pointer-events: none;
  left: 12px;
  top: 12px;
  transition: 0.2s ease all;
  color: #9ca3af;
}
input:focus ~ .floating-label,
input:not(:placeholder-shown) ~ .floating-label {
  top: -8px;
  left: 10px;
  font-size: 12px;
  background-color: white;
  padding: 0 4px;
  color: #7e22ce;
}
</style>