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

type ForgotPasswordView = "form" | "success"

export function ForgotPassword() {
  const [view, setView] = React.useState<ForgotPasswordView>("form")
  const [email, setEmail] = React.useState("")

  function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault()
    // TODO: call forgot-password API
    setView("success")
  }

  return (
    <div className="min-h-screen w-full bg-background text-foreground flex items-center justify-center p-8">
      <Card className="w-full max-w-md">
        {view === "form" ? (
          <>
            <CardHeader className="text-center">
              <CardTitle className="text-2xl font-bold">Forgot Password</CardTitle>
              <CardDescription>
                Enter your email and we'll send you a reset link.
              </CardDescription>
            </CardHeader>
            <CardContent>
              <form onSubmit={handleSubmit}>
                <FieldGroup>
                  <Field>
                    <FieldLabel htmlFor="email">Email address</FieldLabel>
                    <Input
                      id="email"
                      type="email"
                      placeholder="you@example.com"
                      value={email}
                      onChange={(e) => setEmail(e.target.value)}
                      required
                    />
                  </Field>
                  <Button type="submit" className="w-full">
                    Send Reset Link
                  </Button>
                </FieldGroup>
              </form>
            </CardContent>
          </>
        ) : (
          <>
            <CardHeader className="text-center">
              <CardTitle className="text-2xl font-bold">Check Your Email</CardTitle>
              <CardDescription>
                We sent a password reset link to <strong>{email}</strong>.
                Check your inbox and follow the instructions.
              </CardDescription>
            </CardHeader>
            <CardContent>
              <Button
                variant="outline"
                className="w-full"
                onClick={() => setView("form")}
              >
                Back to Login
              </Button>
            </CardContent>
          </>
        )}
      </Card>
    </div>
  )
}

export default ForgotPassword
