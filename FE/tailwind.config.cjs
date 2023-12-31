/**
 * @type {import('tailwindcss').Config}
 */
module.exports = {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}", "./node_modules/tailwind-datepicker-react/dist/**/*.js"],
  theme: {
    extend: {
      fontFamily: {
        light: ["light"],
        regular: ["regular"],
        medium: ["medium"],
        semiBold: ["semibold"],
        bold: ["bold"],
        extraBold: ["extraBold"],
      },
      colors: {
        main: "#6CE061",
        mainDark: "#47B04F",
        mainMoreDark: "#00A347",
        mainLight: "#A0DF92",
        mainMoreLight: "#ADF777",
        bright: "#EDFDE8",
        lime: "#C1E061",
        kakao: "#FEE500",
        primary: "#237bff",
        lightPrimary: "#43A8FF",
        myRed: "#DF1525",
        darkRed: "#bf001a",
        lightRed: "#FF4D4D",
        dark: "#1B4265",
        myGray: "#8A8E92",
      },
    },
  },
  plugins: [],
};
