/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["src/main/webapp/WEB-INF/jsp/**/*.jsp"],
  theme: {
    extend: {
      colors: {
        bgHeader: "#1C1919",
      },
      backgroundImage: {
        bgCustom:
          "url(https://images.unsplash.com/photo-1513104890138-7c749659a591?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80)",
        bgCustom2:
          "url(https://images.unsplash.com/photo-1513104890138-7c749659a591?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=870&q=80)",
        bgCustom3:
          "url(https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1381&q=80)",
      },
    },
  },
  plugins: [],
};
