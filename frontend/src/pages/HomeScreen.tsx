import { Link } from "react-router-dom"
import { Button } from "@/components/ui/button"
import {
    Card,
    CardContent,
    CardDescription,
    CardHeader,
    CardTitle,
} from "@/components/ui/card"
import {
    DropdownMenu,
    DropdownMenuContent,
    DropdownMenuItem,
    DropdownMenuTrigger,
} from "@/components/ui/dropdown-menu"

import { getSavedRecipes, getCookedRecipes } from "@/lib/mockRecipes"
import { getProfile } from "@/lib/mockUserProfile"

export default function HomeScreen() {
    // Frontend-only "logged in user" info (from localStorage / mock)
    const user = getProfile()

    // Frontend-only recipe lists (placeholder)
    const savedRecipes = getSavedRecipes()
    const cookedRecipes = getCookedRecipes()

    return (
        <div className="min-h-screen w-full bg-background text-foreground">

            <header className="w-full border-b">
                <div className="relative mx-auto max-w-6xl px-6 py-4 flex items-center justify-center">

                    {/* App title */}
                    <h1 className="text-2xl font-bold tracking-tight">
                        WhatsForDinner
                    </h1>

                    {/* Top-right profile icon */}
                    <div className="absolute right-6">
                        <DropdownMenu>
                            <DropdownMenuTrigger asChild>
                                <button
                                    className="h-10 w-10 rounded-full overflow-hidden border flex items-center justify-center"
                                    aria-label="Open profile menu"
                                >
                                    <img
                                        src={user.profilePictureUrl}
                                        alt="Profile"
                                        className="h-full w-full object-cover"
                                    />
                                </button>
                            </DropdownMenuTrigger>

                            <DropdownMenuContent align="end">
                                <DropdownMenuItem asChild>
                                    <Link to="/profile-settings">Profile Settings</Link>
                                </DropdownMenuItem>
                            </DropdownMenuContent>
                        </DropdownMenu>
                    </div>
                </div>
            </header>

            {/* 2 buttons under title: Get dinner and Update Pantry */}
            <main className="mx-auto max-w-6xl px-6 py-8">
                <div className="grid grid-cols-1 lg:grid-cols-3 gap-6 items-start">

                    {/* LEFT: Saved recipes */}
                    <Card className="lg:col-span-1">
                        <CardHeader>
                            <CardTitle>Saved recipes</CardTitle>
                            <CardDescription>Your bookmarked recipes</CardDescription>
                        </CardHeader>
                        <CardContent>
                            <div className="max-h-[520px] overflow-y-auto space-y-2 pr-1">
                                {savedRecipes.length === 0 ? (
                                    <p className="text-sm text-muted-foreground">No saved recipes yet.</p>
                                ) : (
                                    savedRecipes.map((r) => (
                                        <div key={r.id} className="border rounded-md px-3 py-2 text-sm">
                                            {r.title}
                                        </div>
                                    ))
                                )}
                            </div>
                        </CardContent>
                    </Card>

                    {/* CENTER: Main actions stacked */}
                    <div className="lg:col-span-1 space-y-6">
                        <Card>
                            <CardHeader>
                                <CardTitle>Get Dinner</CardTitle>
                                <CardDescription>Generate recipes</CardDescription>
                            </CardHeader>
                            <CardContent>
                                <Button className="w-full" onClick={() => alert("Generate recipes later")}>
                                    Get Dinner
                                </Button>
                            </CardContent>
                        </Card>

                        <Card>
                            <CardHeader>
                                <CardTitle>Update Pantry</CardTitle>
                                <CardDescription>Update your pantry after cooking</CardDescription>
                            </CardHeader>
                            <CardContent>
                                <Button
                                    className="w-full"
                                    variant="secondary"
                                    onClick={() => alert("Update pantry later")}
                                >
                                    Update Pantry
                                </Button>
                            </CardContent>
                        </Card>
                    </div>

                    {/* RIGHT: Cooked recipes */}
                    <Card className="lg:col-span-1">
                        <CardHeader>
                            <CardTitle>Cooked recipes</CardTitle>
                            <CardDescription>Previously cooked meals</CardDescription>
                        </CardHeader>
                        <CardContent>
                            <div className="max-h-[520px] overflow-y-auto space-y-2 pr-1">
                                {cookedRecipes.length === 0 ? (
                                    <p className="text-sm text-muted-foreground">No cooked recipes yet.</p>
                                ) : (
                                    cookedRecipes.map((r) => (
                                        <div key={r.id} className="border rounded-md px-3 py-2 text-sm">
                                            {r.title}
                                        </div>
                                    ))
                                )}
                            </div>
                        </CardContent>
                    </Card>

                </div>
            </main>

        </div>
    )
}
