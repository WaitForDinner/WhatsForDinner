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

export function App () {
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
        </CardContent>
      </Card>
    </div>
  )
}

export default App
