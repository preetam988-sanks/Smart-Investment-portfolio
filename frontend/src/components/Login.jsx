import { useState } from 'react';
import api from '../api/axios';
import { useNavigate } from 'react-router-dom';
import './Login.css';

const Login = () => {
    const [credentials, setCredentials] = useState({ email: '', password: '' });
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        setLoading(true);
        try {
            const res = await api.post('/auth/login', credentials);
            localStorage.setItem('token', res.data.token);
            navigate('/dashboard');
        } catch (err) {
            alert("Error: " + (err.response?.data?.error || "Login failed"));
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="login-container">
            <div className="login-card">
                <h2>Welcome Back</h2>
                <p className="subtitle">Sign in to manage your portfolio</p>

                <form onSubmit={handleLogin} className="login-form">
                    <input
                        type="email"
                        placeholder="Email Address"
                        value={credentials.email}
                        onChange={e =>
                            setCredentials({ ...credentials, email: e.target.value })
                        }
                        required
                    />

                    <input
                        type="password"
                        placeholder="Password"
                        value={credentials.password}
                        onChange={e =>
                            setCredentials({ ...credentials, password: e.target.value })
                        }
                        required
                    />

                    <button type="submit" disabled={loading}>
                        {loading ? "Signing In..." : "Sign In"}
                    </button>
                </form>

                <div className="register-section">
                    <p>Don’t have an account?</p>
                    <button
                        className="register-btn"
                        onClick={() => navigate('/register')}
                    >
                        Create Account
                    </button>
                </div>
            </div>
        </div>
    );
};

export default Login;