import { useEffect, useState, useMemo } from 'react';
import api from '../api/axios';
import './Dashboard.css';

const Dashboard = () => {
    const [investments, setInvestments] = useState([]);
    const [adminStats, setAdminStats] = useState(null);
    const [formData, setFormData] = useState({ assetName: '', quantity: '', price: '' });

    const getRoleFromToken = () => {
        const token = localStorage.getItem('token');
        if (!token) return null;
        try {
            const payload = JSON.parse(atob(token.split('.')[1]));
            return payload.role;
        } catch (e) { return null; }
    };

    const role = getRoleFromToken();

    const fetchData = async () => {
        try {
            const invRes = await api.get('/investments');
            setInvestments(Array.isArray(invRes.data) ? invRes.data : []);

            if (role === 'ADMIN') {
                const statsRes = await api.get('/admin/stats');
                setAdminStats(statsRes.data);
            }
        } catch (err) { console.error(err); }
    };

    const handleAdd = async (e) => {
        e.preventDefault();
        try {
            const payload = {
                assetName: formData.assetName,
                quantity: Number(formData.quantity),
                price: Number(formData.price)
            };
            await api.post('/investments', payload);
            setFormData({ assetName: '', quantity: '', price: '' });
            fetchData();
        } catch (err) { alert("Failed to add asset"); }
    };

    const handleDelete = async (id) => {
        if (window.confirm("Delete this investment?")) {
            try {
                await api.delete(`/investments/${id}`);
                fetchData();
            } catch (err) { alert("Delete failed"); }
        }
    };

    useEffect(() => { fetchData(); }, []);

    const totalInvested = useMemo(() =>
        investments.reduce((t, inv) => t + (inv.quantity * inv.buyPrice), 0), [investments]
    );

    const formatCurrency = (val) => new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' }).format(val);

    return (
        <div className="dashboard-container">
            <div className="dashboard-wrapper">

                {role === 'ADMIN' && adminStats && (
                    <div className="admin-card">
                        <div className="badge">ADMIN PANEL</div>
                        <div className="stats-row">
                            <div><p>Total Users</p><h3>{adminStats.totalUsers}</h3></div>
                            <div><p>System Investments</p><h3>{adminStats.totalInvestments}</h3></div>
                        </div>
                    </div>
                )}

                <div className="summary-card">
                    <div>
                        <h3>Total Portfolio Value</h3>
                        <h1>{formatCurrency(totalInvested)}</h1>
                    </div>
                    <div>
                        <p>Total Assets</p>
                        <h2>{investments.length}</h2>
                    </div>
                </div>

                <div className="card">
                    <h2>Add New Investment</h2>
                    <form className="investment-form" onSubmit={handleAdd}>
                        <input type="text" placeholder="Asset Name" value={formData.assetName} onChange={e => setFormData({...formData, assetName: e.target.value})} required />
                        <input type="number" placeholder="Quantity" value={formData.quantity} onChange={e => setFormData({...formData, quantity: e.target.value})} required />
                        <input type="number" placeholder="Buy Price" value={formData.price} onChange={e => setFormData({...formData, price: e.target.value})} required />
                        <button type="submit">Add Asset</button>
                    </form>
                </div>

                <div className="card">
                    <h2>Your Portfolio</h2>
                    <div className="portfolio-list">
                        {investments.length > 0 ? investments.map(inv => (
                            <div className="portfolio-item" key={inv.id}>
                                <div><strong>{inv.assetName}</strong><p>{inv.quantity} Units</p></div>
                                <div className="portfolio-right">
                                    <div className="text-right">
                                        <p className="label">Total Value</p>
                                        <span className="price">{formatCurrency(inv.quantity * inv.buyPrice)}</span>
                                    </div>
                                    <button className="delete-btn" onClick={() => handleDelete(inv.id)}>Delete</button>
                                </div>
                            </div>
                        )) : <p className="empty">No investments yet.</p>}
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Dashboard;