import { useEffect, useMemo, useState } from "react"
import { Link } from "react-router-dom"

import { Button } from "@/components/ui/button"
import { Card, CardContent, CardHeader, CardTitle, CardDescription } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"

import { getProfile, saveProfile, type Profile } from "@/lib/mockUserProfile"

type Errors = Partial<Record<"username" | "email" | "password" | "confirmPassword" | "picture", string>>

export default function ProfileSettings() {
    const [loaded, setLoaded] = useState(false)

    const [username, setUsername] = useState("")
    const [email, setEmail] = useState("")
    const [profilePictureUrl, setProfilePictureUrl] = useState("")
    const [selectedFile, setSelectedFile] = useState<File | null>(null)
    const [password, setPassword] = useState("")
    const [confirmPassword, setConfirmPassword] = useState("")


    const [errors, setErrors] = useState<Errors>({})
    const [successMsg, setSuccessMsg] = useState("")
    const [saving, setSaving] = useState(false)

    // Load current profile info
    useEffect(() => {
        const p = getProfile()
        setUsername(p.name)
        setEmail(p.email)
        setProfilePictureUrl(p.profilePictureUrl)
        setLoaded(true)
    }, [])

    // Local preview for uploaded image BEFORE saving
    const filePreviewUrl = useMemo(() => {
        if (!selectedFile) return ""
        return URL.createObjectURL(selectedFile)
    }, [selectedFile])

    useEffect(() => {
        return () => {
            if (filePreviewUrl) URL.revokeObjectURL(filePreviewUrl)
        }
    }, [filePreviewUrl])

    const validate = (): Errors => {
        const e: Errors = {}

        if (!username.trim()) e.username = "Name is required."
        if (!email.trim()) e.email = "Email is required."
        // Password is optional: only validate if user typed something
        if (password.trim()) {
            if (password.length < 6) e.password = "Password must be at least 6 characters."
            if (password !== confirmPassword) e.confirmPassword = "Passwords do not match."
        }


        const emailRegex = /^\S+@\S+\.\S+$/
        if (email.trim() && !emailRegex.test(email.trim())) {
            e.email = "Invalid email format."
        }

        if (selectedFile) {
            if (!selectedFile.type.startsWith("image/")) {
                e.picture = "Selected file must be an image."
            }
            const maxBytes = 5 * 1024 * 1024
            if (selectedFile.size > maxBytes) {
                e.picture = "Image must be 5MB or smaller."
            }
        }

        return e
    }

    const onSave = async () => {
        setSuccessMsg("")
        const e = validate()
        setErrors(e)
        if (Object.keys(e).length) return

        setSaving(true)
        await new Promise((r) => setTimeout(r, 250)) // frontend-only "save"

        const updated: Profile = {
            name: username.trim(),
            email: email.trim(),
            profilePictureUrl: selectedFile ? filePreviewUrl : profilePictureUrl.trim(),
        }

        saveProfile(updated)
        setSelectedFile(null)
        setSuccessMsg("Profile updated successfully.")
        setSaving(false)
    }

    if (!loaded) return null

    const shownPicture = selectedFile ? filePreviewUrl : profilePictureUrl

    return (
        <div className="min-h-screen w-full flex items-start justify-center p-8">
            <div className="w-full max-w-6xl space-y-6">

                {/* Page title */}
                <div className="flex items-center justify-between">
                    <h1 className="text-2xl font-bold">Profile Settings</h1>

                    <Button variant="ghost" asChild>
                        <Link to="/home">← Back</Link>
                    </Button>
                </div>

                {/* 2-column layout: left edit card, right action button */}
                <div className="grid grid-cols-1 lg:grid-cols-2 gap-10 items-start">

                    {/* LEFT: Edit card */}
                    <Card className="w-full max-w-xl">
                        <CardHeader className="space-y-1">
                            <CardTitle>Edit</CardTitle>
                            <CardDescription>Update your profile information.</CardDescription>
                        </CardHeader>

                        <CardContent className="space-y-5">
                            {/* current profile picture displayed */}
                            <div className="flex flex-col items-center gap-3">
                                <img
                                    src={shownPicture}
                                    alt="Profile"
                                    className="h-24 w-24 rounded-full object-cover border"
                                />
                                <p className="text-xs text-muted-foreground">Current profile picture</p>
                            </div>

                            {/* name */}
                            <div className="space-y-2">
                                <Label htmlFor="username">Username</Label>
                                <Input
                                    id="username"
                                    value={username}
                                    onChange={(e) => setUsername(e.target.value)}
                                    placeholder="Enter your username"
                                />
                                {errors.username && <p className="text-sm text-red-500">{errors.username}</p>}
                            </div>

                            {/* email */}
                            <div className="space-y-2">
                                <Label htmlFor="email">Email</Label>
                                <Input
                                    id="email"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                    placeholder="Enter your email"
                                />
                                {errors.email && <p className="text-sm text-red-500">{errors.email}</p>}
                            </div>
                            {/* password */}
                            <div className="space-y-2">
                                <Label htmlFor="password">Password</Label>
                                <Input
                                    id="password"
                                    type="password"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    placeholder="Enter a new password"
                                />
                                {errors.password && <p className="text-sm text-red-500">{errors.password}</p>}
                            </div>

                            {/* confirm password */}
                            <div className="space-y-2">
                                <Label htmlFor="confirmPassword">Confirm password</Label>
                                <Input
                                    id="confirmPassword"
                                    type="password"
                                    value={confirmPassword}
                                    onChange={(e) => setConfirmPassword(e.target.value)}
                                    placeholder="Confirm your new password"
                                />
                                {errors.confirmPassword && <p className="text-sm text-red-500">{errors.confirmPassword}</p>}
                            </div>

                            {/* upload picture */}
                            <div className="space-y-2">
                                <Label htmlFor="upload">Upload new profile picture</Label>
                                <Input
                                    id="upload"
                                    type="file"
                                    accept="image/*"
                                    onChange={(e) => setSelectedFile(e.target.files?.[0] ?? null)}
                                />
                                {errors.picture && <p className="text-sm text-red-500">{errors.picture}</p>}
                            </div>

                            {/* Confirmation message */}
                            {successMsg && <p className="text-sm text-green-600">{successMsg}</p>}

                            {/* Save Changes (required by feature file) */}
                            <Button className="w-full" onClick={onSave} disabled={saving}>
                                {saving ? "Saving..." : "Save Changes"}
                            </Button>
                        </CardContent>
                    </Card>

                    {/* RIGHT: Dietary preferences button */}
                    <div className="w-full flex justify-center lg:justify-start pt-6">
                        <button
                            className="w-full max-w-sm h-24 rounded-2xl bg-primary text-primary-foreground text-xl font-semibold shadow-md hover:opacity-90 transition"
                            onClick={() => alert("Update dietary preferences later")}
                        >
                            <div className="flex flex-col items-center">
                                <span>Update Dietary Preferences</span>
                                <span className="text-sm font-normal opacity-80">
                                    Manage your dietary settings
                                </span>
                            </div>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    )

}
