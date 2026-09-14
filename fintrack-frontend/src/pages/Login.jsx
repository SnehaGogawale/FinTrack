import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Eye, EyeOff, ArrowRight, ShieldCheck } from "lucide-react";

import api from "../services/api";

function Login() {

    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        email: "",
        password: ""
    });

    const [showPassword, setShowPassword] = useState(false);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    const handleChange = (event) => {

        const { name, value } = event.target;

        setFormData((previous) => ({
            ...previous,
            [name]: value
        }));

        setError("");
    };

    const handleSubmit = async (event) => {

        event.preventDefault();

        if (!formData.email || !formData.password) {
            setError("Please enter your email and password.");
            return;
        }

        try {

            setLoading(true);
            setError("");

            const response = await api.post(
                "/users/login",
                formData
            );

            const data = response.data;

            localStorage.setItem("token", data.token);
            localStorage.setItem("userId", data.userId);
            localStorage.setItem("userName", data.name);
            localStorage.setItem("userEmail", data.email);

            navigate("/dashboard");

        } catch (error) {

            const message =
                error.response?.data?.message ||
                "Unable to sign in. Please check your credentials.";

            setError(message);

        } finally {

            setLoading(false);
        }
    };

    return (
        <div className="auth-page">

            <div className="auth-container">

                {/* LEFT SIDE */}

                <div className="auth-brand-panel">

                    <div className="brand-logo">
                        <div className="brand-icon">
                            F
                        </div>

                        <span>FinTrack</span>
                    </div>

                    <div className="brand-content">

                        <span className="brand-badge">
                            PERSONAL FINANCE
                        </span>

                        <h1>
                            Take control of
                            <span> your money.</span>
                        </h1>

                        <p>
                            Track your spending, understand your habits,
                            and build a clearer path toward your financial goals.
                        </p>

                    </div>

                    <div className="brand-security">
                        <ShieldCheck size={18} />

                        <span>
                            Your financial data stays protected
                        </span>
                    </div>

                </div>


                {/* RIGHT SIDE */}

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
                                WELCOME BACK
                            </span>

                            <h2>
                                Sign in to your account
                            </h2>

                            <p>
                                Enter your details to continue to FinTrack.
                            </p>

                        </div>


                        {error && (
                            <div className="auth-error">
                                {error}
                            </div>
                        )}


                        <form onSubmit={handleSubmit}>

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

                                <div className="label-row">

                                    <label htmlFor="password">
                                        Password
                                    </label>

                                    <button
                                        type="button"
                                        className="forgot-link"
                                    >
                                        Forgot password?
                                    </button>

                                </div>


                                <div className="password-input">

                                    <input
                                        id="password"
                                        name="password"
                                        type={
                                            showPassword
                                                ? "text"
                                                : "password"
                                        }
                                        placeholder="Enter your password"
                                        value={formData.password}
                                        onChange={handleChange}
                                        autoComplete="current-password"
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
                                    "Signing in..."
                                ) : (
                                    <>
                                        Sign in
                                        <ArrowRight size={18} />
                                    </>
                                )}

                            </button>

                        </form>


                        <div className="auth-divider">
                            <span>New to FinTrack?</span>
                        </div>


                        <Link
                            to="/register"
                            className="register-link"
                        >
                            Create an account
                        </Link>


                        <p className="auth-footer">
                            By continuing, you agree to our Terms
                            and Privacy Policy.
                        </p>

                    </div>

                </div>

            </div>

        </div>
    );
}

export default Login;