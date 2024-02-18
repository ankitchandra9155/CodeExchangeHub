const isLocal=process.env.NODE_ENV==='development'
console.log(isLocal);
const apiUrl=isLocal
? "http://localhost:8080" : "https://codeexchangehub-production.up.railway.app"
console.log(apiUrl);

export default apiUrl;

