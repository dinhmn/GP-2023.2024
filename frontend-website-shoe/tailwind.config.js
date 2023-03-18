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
      },
      backgroundColor: {
        'brown': '#cc334d',
        'brown-hover': '#ad2a40'
      },
      textColor: {
        'brown': '#cc334d',
      }
    },
  },
  plugins: [],
}
