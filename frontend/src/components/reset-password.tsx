import * as React from "react"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card"
import { Field, FieldGroup, FieldLabel } from "@/components/ui/field"

type Errors = {
  password?: string
  confirmPassword?: string
}

export function ResetPassword() {
  const [password, setPassword] = React.useState("")
  const [confirmPassword, setConfirmPassword] = React.useState("")
  const [errors, setErrors] = React.useState<Errors>({})
  const [submitted, setSubmitted] = React.useState(false)

  function validate(): Errors {
    const newErrors: Errors = {}

    if (!password) {
      newErrors.password = "Password is required"
    } else if (password.length < 8) {
      newErrors.password = "Password must be at least 8 characters"
    }

    if (!confirmPassword) {
      newErrors.confirmPassword = "Please confirm your password"
    } else if (password !== confirmPassword) {
      newErrors.confirmPassword = "Passwords do not match"
    }

    return newErrors
  }

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault()

    const validationErrors = validate()
    setErrors(validationErrors)

    if (Object.keys(validationErrors).length > 0) {
      return
    }

    // TODO: call reset-password API with token + password
    setSubmitted(true)
  }

  return (
      <div className="min-h-screen w-full bg-background text-foreground flex items-center justify-center p-8">
        <Card className="w-full max-w-md">
          {!submitted ? (
              <>
                <CardHeader className="text-center">
                  <CardTitle className="text-2xl font-bold">
                    Reset Password
                  </CardTitle>
                  <CardDescription>
                    Enter a new password for your account.
                  </CardDescription>
                </CardHeader>

                <CardContent>
                  <form onSubmit={handleSubmit}>
                    <FieldGroup>
                      <Field>
                        <FieldLabel htmlFor="password">
                          New Password
                        </FieldLabel>
                        <Input
                            id="password"
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                        />
                        {errors.password && (
                            <p className="text-sm text-destructive">
                              {errors.password}
                            </p>
                        )}
                      </Field>

                      <Field>
                        <FieldLabel htmlFor="confirmPassword">
                          Confirm Password
                        </FieldLabel>
                        <Input
                            id="confirmPassword"
                            type="password"
                            value={confirmPassword}
                            onChange={(e) => setConfirmPassword(e.target.value)}
                        />
                        {errors.confirmPassword && (
                            <p className="text-sm text-destructive">
                              {errors.confirmPassword}
                            </p>
                        )}
                      </Field>

                      <Button type="submit" className="w-full">
                        Reset Password
                      </Button>
                    </FieldGroup>
                  </form>
                </CardContent>
              </>
          ) : (
              <>
                <CardHeader className="text-center">
                  <CardTitle className="text-2xl font-bold">
                    Password Reset Successful
                  </CardTitle>
                  <CardDescription>
                    Your password has been updated. You can now log in
                    with your new password.
                  </CardDescription>
                </CardHeader>

                <CardContent>
                  <Button variant="outline" className="w-full">
                    Back to Login
                  </Button>
                </CardContent>
              </>
          )}
        </Card>
      </div>
  )
}

export default ResetPassword