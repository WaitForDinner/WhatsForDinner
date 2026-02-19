import { Button } from "@/components/ui/button"
import { Card, CardContent } from "@/components/ui/card"
import { Link } from "react-router-dom"

export default function HomeScreen() {
    return (
        <div className="min-h-screen flex items-center justify-center">
            <Card className="w-full max-w-md">
                <CardContent className="p-8 space-y-4">
                    <h1 className="text-xl font-semibold">Welcome to the Home Screen</h1>
                    <p className="text-sm text-muted-foreground">Home screen placeholder (dashboard not implemented yet).</p>
                    <Button asChild className="w-full">
                        <Link to="/profile-settings">Go to Profile Settings</Link>
                    </Button>
                </CardContent>
            </Card>
        </div>
    )
}   