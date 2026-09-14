import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import {
    Eye,
    EyeOff,
    ArrowRight,
    ShieldCheck,
    CheckCircle2
} from "lucide-react";

import api from "../services/api";

function Register() {

    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        name: "",
        email: "",
        password: ""
    });

    const [showPassword, setShowPassword] = useState(false);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");
    const [success, setSuccess] = useState("");

    const handleChange = (event) => {

        const { name, value } = event.target;

        setFormData((previous) => ({
            ...previous,
            [name]: value
        }));

        setError("");
        setSuccess("");
    };

    const handleSubmit = async (event) => {

        event.preventDefault();

        if (!formData.name.trim()) {
            setError("Please enter your name.");
            return;
        }

        if (!formData.email.trim()) {
            setError("Please enter your email address.");
            return;
        }

        if (formData.password.length < 6) {
            setError("Password must contain at least 6 characters.");
            return;
        }

        try {

            setLoading(true);
            setError("");

            await api.post("/users", formData);

            setSuccess(
                "Account created successfully. Redirecting to login..."
            );

            setTimeout(() => {
                navigate("/login");
            }, 1200);

        } catch (error) {

            const message =
                error.response?.data?.message ||
                "Unable to create your account.";

            setError(message);

        } finally {

            setLoading(false);
        }
    };

    return (
        <div className="auth-page">

            <div className="auth-container">

                {/* LEFT BRAND PANEL */}

                <div className="auth-brand-panel">

                    <div className="brand-logo">

                        <div className="brand-icon">
                            F
                        </div>

                        <span>FinTrack</span>

                    </div>

                    <div className="brand-content">

                        <span className="brand-badge">
                            START YOUR JOURNEY
                        </span>

                        <h1>
                            Build better
                            <span> money habits.</span>
                        </h1>

                        <p>
                            Create your personal finance space and
                            start understanding where your money goes.
                        </p>

                        <div className="register-benefits">

                            <div className="benefit-item">
                                <CheckCircle2 size={18} />
                                <span>Track income and expenses</span>
                            </div>

                            <div className="benefit-item">
                                <CheckCircle2 size={18} />
                                <span>Understand your spending</span>
                            </div>

                            <div className="benefit-item">
                                <CheckCircle2 size={18} />
                                <span>See your financial progress</span>
                            </div>

                        </div>

                    </div>

                    <div className="brand-security">

                        <ShieldCheck size={18} />

                        <span>
                            Your financial data stays protected
                        </span>

                    </div>

                </div>


                {/* FORM PANEL */}

                <div className="auth-form-panel">

                    <div className="auth-form-wrapper">

                        <div className="mobile-brand">

                            <div className="brand-icon">
                                F
                            </div>

                            <span>FinTrack</span>

                        </div>


                        <div className="form-heading">

                            <span className="form-eyebrow">
                                CREATE ACCOUNT
                            </span>

                            <h2>
                                Start your FinTrack journey
                            </h2>

                            <p>
                                Create an account to manage your finances
                                in one place.
                            </p>

                        </div>


                        {error && (
                            <div className="auth-error">
                                {error}
                            </div>
                        )}


                        {success && (
                            <div className="auth-success">
                                <CheckCircle2 size={17} />
                                <span>{success}</span>
                            </div>
                        )}


                        <form onSubmit={handleSubmit}>

                            <div className="form-group">

                                <label htmlFor="name">
                                    Full name
                                </label>

                                <input
                                    id="name"
                                    name="name"
                                    type="text"
                                    placeholder="Enter your name"
                                    value={formData.name}
                                    onChange={handleChange}
                                    autoComplete="name"
                                />

                            </div>


                            <div className="form-group">

                                <label htmlFor="email">
                                    Email address
                                </label>

                                <input
                                    id="email"
                                    name="email"
                                    type="email"
                                    placeholder="you@example.com"
                                    value={formData.email}
                                    onChange={handleChange}
                                    autoComplete="email"
                                />

                            </div>


                            <div className="form-group">

                                <label htmlFor="password">
                                    Password
                                </label>

                                <div className="password-input">

                                    <input
                                        id="password"
                                        name="password"
                                        type={
                                            showPassword
                                                ? "text"
                                                : "password"
                                        }
                                        placeholder="At least 6 characters"
                                        value={formData.password}
                                        onChange={handleChange}
                                        autoComplete="new-password"
                                    />

                                    <button
                                        type="button"
                                        className="password-toggle"
                                        onClick={() =>
                                            setShowPassword(
                                                !showPassword
                                            )
                                        }
                                        aria-label={
                                            showPassword
                                                ? "Hide password"
                                                : "Show password"
                                        }
                                    >
                                        {showPassword ? (
                                            <EyeOff size={19} />
                                        ) : (
                                            <Eye size={19} />
                                        )}
                                    </button>

                                </div>

                            </div>


                            <button
                                type="submit"
                                className="auth-submit"
                                disabled={loading}
                            >

                                {loading ? (
                                    "Creating account..."
                                ) : (
                                    <>
                                        Create account
                                        <ArrowRight size={18} />
                                    </>
                                )}

                            </button>

                        </form>


                        <div className="auth-divider">
                            <span>Already have an account?</span>
                        </div>


                        <Link
                            to="/login"
                            className="register-link"
                        >
                            Sign in instead
                        </Link>


                        <p className="auth-footer">
                            By creating an account, you agree to our
                            Terms and Privacy Policy.
                        </p>

                    </div>

                </div>

            </div>

        </div>
    );
}

export default Register;