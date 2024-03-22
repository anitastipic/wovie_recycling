import React, { createContext, useContext, useState, ReactNode } from 'react';

type Profile = {
    username: string;
};

type AuthContextType = {
    authorized: boolean;
    profile: Profile | null;
    login: (profile: Profile) => void;
    logout: () => void;
    authorize: () => void;
};

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const useAuth = () => {
    const context = useContext(AuthContext);
    if (!context) {
        throw new Error('useAuth must be used within an AuthProvider');
    }
    return context;
};

type AuthProviderProps = {
    children: ReactNode;
};

export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
    const [authState, setAuthState] = useState<{ authorized: boolean; profile: Profile | null }>({
        authorized: false,
        profile: null
    });

    const login = (profile: Profile) => {
        setAuthState({ authorized: true, profile });
    };

    const authorize = () => {
        setAuthState(prevState => ({ ...prevState, authorized: true })); // Corrected
    };

    const logout = () => {
        setAuthState({ authorized: false, profile: null });
    };

    return (
        <AuthContext.Provider value={{ ...authState, login, logout, authorize}}>
    {children}
    </AuthContext.Provider>
);
};
