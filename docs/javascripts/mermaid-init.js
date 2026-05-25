document.addEventListener("DOMContentLoaded", function () {
  if (typeof mermaid !== "undefined") {
    const isDark = document.body.dataset.mdColorScheme === "slate";
    mermaid.initialize({
      startOnLoad: true,
      theme: isDark ? "dark" : "default",
      themeVariables: {
        primaryColor: "#00D4FF",
        primaryTextColor: "#0B1A3E",
        primaryBorderColor: "#0B1A3E",
        lineColor: "#0B1A3E",
        fontFamily: "Inter, sans-serif"
      }
    });
  }
});
