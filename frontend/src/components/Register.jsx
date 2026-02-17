import { useState } from 'react';
import api from '../api/axios';
import { useNavigate, Link } from 'react-router-dom';
import './Auth.css';

const Register = () => {
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        password: ''
    });

    const navigate = useNavigate();

    const handleRegister = async (e) => {
        e.preventDefault();
        try {
            const res = await api.post('/auth/register', formData);
            localStorage.setItem('token', res.data.token);
            navigate('/dashboard');
        } catch (err) {
            alert("Registration failed: " + (err.response?.data?.error || "Check your details"));
        }
    };

    return (
        <div className="auth-container">
            <div className="auth-card">
                <h2>Create Account</h2>
                <p className="subtitle">Start managing your investments today</p>

                <form onSubmit={handleRegister}>
                    <input
                        type="text"
                        placeholder="Full Name"
                        onChange={e => setFormData({...formData, name: e.target.value})}
                        required
                    />

                    <input
                        type="email"
                        placeholder="Email Address"
                        onChange={e => setFormData({...formData, email: e.target.value})}
                        required
                    />

                    <input
                        type="password"
                        placeholder="Password (min 6 characters)"
                        onChange={e => setFormData({...formData, password: e.target.value})}
                        required
                    />

                    <button type="submit">Create Account</button>
                </form>

                <p className="redirect">
                    Already have an account? <Link to="/login">Login</Link>
                </p>
            </div>
        </div>
    );
};

export default Register;