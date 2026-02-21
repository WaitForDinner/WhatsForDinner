import * as React from 'react'
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
import { ForgotPassword } from '@/components/forgot-password'
import { DeleteAccount } from '@/components/delete-account'
import { ResetPassword } from '@/components/reset-password'

type Page = 'home' | 'forgot-password' | 'reset-password' | 'delete-account'

export function App () {
  const [page, setPage] = React.useState<Page>('home')

  if (page === 'forgot-password') {
    return (
        <div>
          <div className='fixed top-4 left-4 z-50'>
            <Button variant='outline' size='sm' onClick={() => setPage('home')}>
              ← Back
            </Button>
          </div>
          <ForgotPassword />
        </div>
    )
  }

  if (page === 'reset-password') {
    return (
        <div>
          <div className='fixed top-4 left-4 z-50'>
            <Button variant='outline' size='sm' onClick={() => setPage('home')}>
              ← Back
            </Button>
          </div>
          <ResetPassword />
        </div>
    )
  }

  if (page === 'delete-account') {
    return (
        <div>
          <div className='fixed top-4 left-4 z-50'>
            <Button variant='outline' size='sm' onClick={() => setPage('home')}>
              ← Back
            </Button>
          </div>
          <DeleteAccount />
        </div>
    )
  }

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

            <div className='border-t pt-4 flex flex-col gap-2'>
              <p className='text-xs text-muted-foreground text-center font-medium uppercase tracking-wide'>
                Dev Preview
              </p>
              <Button variant='outline' className='w-full' onClick={() => setPage('forgot-password')}>
                Forgot Password
              </Button>
              <Button variant='outline' className='w-full' onClick={() => setPage('delete-account')}>
                Delete Account
              </Button>
              <Button variant='outline' className='w-full' onClick={() => setPage('reset-password')}>
                Reset Password
              </Button>
            </div>
          </CardContent>
        </Card>
      </div>
  )
}

export default App