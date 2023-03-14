/* eslint-disable no-undef */
/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      fontFamily: {
          'grotesk':['Space Grotesk', 'sans-serif'],
          'poppins': ['Poppins', 'sans-serif']
      }
    },
  },
  plugins: [],
}
