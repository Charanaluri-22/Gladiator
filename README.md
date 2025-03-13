# Gladiator Project

## Overview
This project is a web application for managing stocks and trading portfolios. It allows users to buy/sell stocks, manage their portfolio, and track their investments.

## Features
- User authentication and authorization
- Real-time stock price tracking
- Portfolio management
- Buy/Sell stock transactions
- Transaction history
- User profile management

## Prerequisites
- Node.js (v14.x or higher)
- MySQL (v8.x or higher)
- Docker (optional, for containerized deployment)

## Local Setup

1. Clone the repository:
```bash
git clone https://github.com/yourusername/gladiator.git
cd gladiator
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
DB_NAME=gladiator_db
JWT_SECRET=your_jwt_secret
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

## Docker Deployment

1. Build the Docker image:
```bash
docker build -t gladiator-app .
```

2. Run the container:
```bash
docker run -d -p 3000:3000 \
  -e DB_HOST=your_db_host \
  -e DB_USER=your_db_user \
  -e DB_PASS=your_db_password \
  -e DB_NAME=gladiator_db \
  -e JWT_SECRET=your_jwt_secret \
  gladiator-app
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

## API Documentation
API documentation can be found at `/api-docs` when running the application.

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
