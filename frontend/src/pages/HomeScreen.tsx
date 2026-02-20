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
        <div className="min-h-screen w-full bg-background text-foreground flex flex-col justify-center">

            <main className="mx-auto max-w-6xl px-6 py-8 w-full">

                {/* TOP: Title + Profile row */}
                <div className="relative flex items-center justify-center mb-8">

                    {/* Title in the center */}
                    <h1 className="text-2xl font-bold tracking-tight">
                        WhatsForDinner
                    </h1>

                    {/* Profile button on the right */}
                    <div className="absolute right-0 flex items-center gap-3">
                        <Link to="/profile-settings"
                            className="flex items-center gap-3 px-3 py-2 rounded-md hover:bg-muted transition-colors">
                            <img
                                src={user.profilePictureUrl}
                                className="h-10 w-10 rounded-full object-cover border"
                            />
                            <span className="text-sm font-medium">
                                Profile Settings
                            </span>
                        </Link>
                    </div>
                </div>

                <div className="grid grid-cols-1 lg:grid-cols-3 gap-6 items-start">

                    {/* LEFT: Saved recipes */}
                    <Card className="lg:col-span-1">
                        <CardHeader className="text-center">
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

                    {/* MIDDLE: 2 buttons under the title: Get dinner and Update Pantry */}
                    <div className="lg:col-span-1 flex flex-col justify-center space-y-8">

                        {/* Get Dinner Button */}
                        <button
                            className="w-full h-24 rounded-2xl bg-primary text-primary-foreground text-xl font-semibold shadow-md hover:opacity-90 transition"
                            onClick={() => alert("Generate recipes later")}
                        >
                            <div className="flex flex-col items-center">
                                <span>Get Dinner</span>
                                <span className="text-sm font-normal opacity-80">
                                    Generate recipes
                                </span>
                            </div>
                        </button>

                        {/* Update Pantry Button */}
                        <button
                            className="w-full h-24 rounded-2xl bg-primary text-primary-foreground text-xl font-semibold shadow-md hover:opacity-90 transition"
                            onClick={() => alert("Update pantry later")}
                        >
                            <div className="flex flex-col items-center">
                                <span>Update Pantry</span>
                                <span className="text-sm font-normal opacity-80">
                                    Update your pantry after cooking
                                </span>
                            </div>
                        </button>
                    </div>

                    {/* RIGHT: Cooked recipes */}
                    <Card className="lg:col-span-1">
                        <CardHeader className="text-center">
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
