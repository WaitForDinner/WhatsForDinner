import { useEffect, useMemo, useState } from "react"
import { Link } from "react-router-dom"

import { Button } from "@/components/ui/button"
import { Card, CardContent, CardHeader, CardTitle, CardDescription } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"

import { getProfile, saveProfile, type Profile } from "@/lib/mockUserProfile"

type Errors = Partial<Record<"name" | "email" | "picture", string>>

export default function ProfileSettings() {
  const [loaded, setLoaded] = useState(false)

  const [name, setName] = useState("")
  const [email, setEmail] = useState("")
  const [profilePictureUrl, setProfilePictureUrl] = useState("")
  const [selectedFile, setSelectedFile] = useState<File | null>(null)

  const [errors, setErrors] = useState<Errors>({})
  const [successMsg, setSuccessMsg] = useState("")
  const [saving, setSaving] = useState(false)

  // Load current profile info (Scenario: Profile information is displayed)
  useEffect(() => {
    const p = getProfile()
    setName(p.name)
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

    if (!name.trim()) e.name = "Name is required."
    if (!email.trim()) e.email = "Email is required."

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
    } else if (profilePictureUrl.trim()) {
      const url = profilePictureUrl.trim()
      if (!(url.startsWith("http://") || url.startsWith("https://"))) {
        e.picture = "Image URL must start with http:// or https://"
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
      name: name.trim(),
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
    <div className="min-h-screen w-full flex items-center justify-center p-8">
      <Card className="w-full max-w-md">
        <CardHeader className="space-y-1">
          <div className="flex items-center justify-between">
            <Button variant="ghost" asChild>
              <Link to="/home">← Back</Link>
            </Button>
            <div className="w-16" />
          </div>
          <CardTitle>Profile Settings</CardTitle>
          <CardDescription>Edit your name, email, and profile picture.</CardDescription>
        </CardHeader>

        <CardContent className="space-y-5">
          {/* current profile picture displayed */}
          <div className="flex flex-col items-center gap-3">
            <img
              src={shownPicture || "https://via.placeholder.com/150"}
              alt="Profile"
              className="h-24 w-24 rounded-full object-cover border"
            />
            <p className="text-xs text-muted-foreground">Current profile picture</p>
          </div>

          {/* name */}
          <div className="space-y-2">
            <Label htmlFor="name">Name</Label>
            <Input
              id="name"
              value={name}
              onChange={(e) => setName(e.target.value)}
              placeholder="Enter your name"
            />
            {errors.name && <p className="text-sm text-red-500">{errors.name}</p>}
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

          {/* upload picture */}
          <div className="space-y-2">
            <Label htmlFor="upload">Upload new profile picture</Label>
            <Input
              id="upload"
              type="file"
              accept="image/*"
              onChange={(e) => setSelectedFile(e.target.files?.[0] ?? null)}
            />
          </div>

          {/* OR picture URL */}
          <div className="space-y-2">
            <Label htmlFor="picUrl">Or profile picture URL</Label>
            <Input
              id="picUrl"
              value={profilePictureUrl}
              onChange={(e) => setProfilePictureUrl(e.target.value)}
              placeholder="https://..."
              disabled={!!selectedFile}
            />
            {errors.picture && <p className="text-sm text-red-500">{errors.picture}</p>}
            {!!selectedFile && (
              <p className="text-xs text-muted-foreground">
                URL input disabled while a file is selected.
              </p>
            )}
          </div>

          {/* messages */}
          {successMsg && <p className="text-sm text-green-600">{successMsg}</p>}

          {/* Save Changes */}
          <Button className="w-full" onClick={onSave} disabled={saving}>
            {saving ? "Saving..." : "Save Changes"}
          </Button>
        </CardContent>
      </Card>
    </div>
  )
}
