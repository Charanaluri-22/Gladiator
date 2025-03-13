# E-Learning Platform

## Overview
This project is a comprehensive e-learning platform that enables users to browse and purchase courses while allowing administrators to manage course content and user orders. The application features a role-based access control system with distinct functionalities for users and administrators.

## Features

### Admin Features
- Course Management
  - Add new courses
  - Edit existing courses
  - Delete courses
- Order Management
  - View all orders
  - Accept/Reject orders
- User Management
  - View registered users
  - Manage user roles

### User Features
- Course Access
  - Browse available courses
  - View course details
  - Add courses to cart
- Shopping Cart
  - Manage cart items
  - Checkout process
- Order Management
  - View order history
  - Track order status
- Review System
  - Add course reviews
  - Rate courses

## Prerequisites
- Node.js (v14.x or higher)
- MySQL (v8.x or higher)
- Docker (optional, for containerized deployment)

## Local Setup

1. Clone the repository:
```bash
git clone https://github.com/yourusername/elearning-platform.git
cd elearning-platform
```

2. Install dependencies:
```bash
npm install
```

3. Configure environment variables:
Create a `.env` file in the root directory and add:
```
DB_HOST=localhost
DB_USER=your_username
DB_PASS=your_password
DB_NAME=elearning_db
JWT_SECRET=your_jwt_secret
STRIPE_SECRET_KEY=your_stripe_secret
STRIPE_PUBLIC_KEY=your_stripe_public
```

4. Initialize the database:
```bash
npm run db:migrate
npm run db:seed
```

5. Start the development server:
```bash
npm run dev
```

The application will be available at `http://localhost:3000`

## API Endpoints

### Admin Routes
- POST /api/admin/courses - Add new course
- PUT /api/admin/courses/:id - Update course
- DELETE /api/admin/courses/:id - Delete course
- GET /api/admin/orders - View all orders
- PUT /api/admin/orders/:id - Update order status

### User Routes
- GET /api/courses - List all courses
- GET /api/courses/:id - Get course details
- POST /api/cart - Add to cart
- GET /api/cart - View cart
- POST /api/orders - Place order
- GET /api/orders - View user orders
- POST /api/reviews - Add course review

## Docker Deployment

1. Build the Docker image:
```bash
docker build -t elearning-app .
```

2. Run the container:
```bash
docker run -d -p 3000:3000 \
  -e DB_HOST=your_db_host \
  -e DB_USER=your_db_user \
  -e DB_PASS=your_db_password \
  -e DB_NAME=elearning_db \
  -e JWT_SECRET=your_jwt_secret \
  -e STRIPE_SECRET_KEY=your_stripe_secret \
  -e STRIPE_PUBLIC_KEY=your_stripe_public \
  elearning-app
```

### Using Docker Compose

1. Create a docker-compose.yml file (included in the repository)

2. Start the application:
```bash
docker-compose up -d
```

## Testing
Run the test suite:
```bash
npm test
```

## Contributing
1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License
This project is licensed under the MIT License - see the LICENSE file for details.

## Support
For support, please open an issue in the GitHub repository or contact the development team.
