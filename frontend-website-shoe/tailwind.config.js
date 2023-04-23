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
        'brown-hover': '#ad2a40',
        'dark-red': 'rgba(255, 35, 72, 0.302)',
        'cyan-bluish-gray': '#abb8c3',
        'dark-blue': '#0c3247',
        'dark-blue-hover': '#135070'
      },
      textColor: {
        'brown': '#cc334d',
        'cyanBlue': '#17b1ea'
      },
      boxShadow: {
        'custom': 'rgba(255, 255, 255, 0.3) 0px 1px 2px 0px, rgba(255, 255, 255, 0.15) 0px 2px 6px 2px'
      }
    },
  },
  plugins: [],
}
