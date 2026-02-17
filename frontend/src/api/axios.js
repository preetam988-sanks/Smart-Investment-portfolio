import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8081/api/v1'
});

api.interceptors.request.use((config) => {
    const token = localStorage.getItem('token');
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});

api.interceptors.response.use(
    (response) => response,
    (error) => {
        if (error.response && error.response.status === 401) {
            localStorage.removeItem('token');
            alert("Session expired. Please login again.");
            window.location.href = "/login";
        }
        return Promise.reject(error);
    }
);
export const getUserRole = () => {
    const token = localStorage.getItem('token');
    if (!token) return null;
    try {
        const base64Url = token.split('.')[1];
        const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
        const payload = JSON.parse(window.atob(base64));
        return payload.role; // This matches the "role" claim in your JwtService.java
    } catch (e) {
        return null;
    }
};

export default api;