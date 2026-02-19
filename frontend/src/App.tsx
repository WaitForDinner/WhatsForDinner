import { BrowserRouter, Routes, Route, Link } from 'react-router-dom'
import HomeScreen from './pages/HomeScreen'
import ProfileSettings from './pages/ProfileSettings'

import { Button } from '@/components/ui/button'
import { Badge } from '@/components/ui/badge'
import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle
} from '@/components/ui/card'
import { Input } from '@/components/ui/input'

function AppContent() {
  return (
    <div className='min-h-screen w-full bg-background text-foreground flex items-center justify-center p-8'>
      <Card className='w-full max-w-md'>
        <CardHeader className='text-center'>
          <CardTitle className='text-3xl font-bold'>
            🍽️ WhatsForDinner
          </CardTitle>
          <CardDescription>This is a test card.</CardDescription>
        </CardHeader>

        <CardContent className='space-y-4'>
          <div className='flex flex-wrap gap-2 justify-center'>
            <Badge>React</Badge>
            <Badge variant='secondary'>Vite</Badge>
            <Badge variant='outline'>Tailwind</Badge>
            <Badge>shadcn/ui</Badge>
          </div>

          <Input placeholder='Try typing here...' />

          <div className='flex gap-2'>
            <Button className='flex-1'>Primary</Button>
            <Button variant='secondary' className='flex-1'>
              Secondary
            </Button>
            <Button variant='outline' className='flex-1'>
              Outline
            </Button>
          </div>

          <p className='text-sm text-muted-foreground text-center'>
            This is a temporary test page to verify tailwind/shadcn integration.
          </p>

          <Button asChild className='w-full'>
            <Link to="/home">Go to Home Screen (TO IMPLEMENT IN WEEK 3 SPRINT A) </Link>
          </Button>

        </CardContent>
      </Card>
    </div>
  )
}

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<AppContent />} />
        <Route path="/home" element={<HomeScreen />} />
        <Route path="/profile-settings" element={<ProfileSettings />} />
      </Routes>
    </BrowserRouter>
  )
}
