/* eslint-disable no-undef */
/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
  theme: {
    screens: {
      'mobile': {'min': '300px', 'max': '639px'},
      'tablet': '640px',
      // => @media (min-width: 640px) { ... }

      'laptop': '1024px',
      // => @media (min-width: 1024px) { ... }

      'desktop': '1280px',
      // => @media (min-width: 1280px) { ... }
    },
    extend: {
      fontFamily: {
          'grotesk':['Space Grotesk', 'sans-serif'],
          'poppins': ['Poppins', 'sans-serif']
      },
      backgroundColor: {
        'brown': '#cc334d',
        'brown-hover': '#ad2a40',
        'dark-red': 'rgba(255, 35, 72, 0.302)'
      },
      textColor: {
        'brown': '#cc334d',
      },
      boxShadow: {
        'custom': 'rgba(255, 255, 255, 0.3) 0px 1px 2px 0px, rgba(255, 255, 255, 0.15) 0px 2px 6px 2px'
      }
    },
  },
  plugins: [],
}
