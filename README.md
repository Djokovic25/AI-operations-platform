# OpsPilot ⚙️
### AI-Powered Operations Platform for Distributed Systems

OpsPilot is an AI-assisted operations platform that helps investigate failures and inconsistencies across distributed services using **Model Context Protocol (MCP)**.

Instead of giving an AI agent direct access to databases or infrastructure, OpsPilot exposes **controlled operational tools** through MCP, allowing the agent to inspect service state and reason about incidents across service boundaries.

## Architecture

```text
                ┌──────────────┐
                │   AI Agent   │
                └──────┬───────┘
                       │ MCP
                       ▼
                ┌──────────────┐
                │ MCP Server   │
                └──────┬───────┘
                       │
              ┌────────┴────────┐
              ▼                 ▼
       ┌────────────┐    ┌─────────────┐
       │   Order    │───▶│   Payment   │
       │  Service   │REST│   Service   │
       └─────┬──────┘    └──────┬──────┘
             ▼                  ▼
       ┌──────────┐       ┌──────────┐
       │PostgreSQL│       │PostgreSQL│
       └──────────┘       └──────────┘
```

## What It Solves

Distributed failures can leave services with inconsistent states.

For example:

```text
Order Service   → PAYMENT_PENDING
Payment Service → SUCCESS
```

OpsPilot allows an AI agent to inspect both services through MCP, correlate the evidence, and explain the inconsistency instead of relying on isolated service state.

## Key Engineering Concepts

- **Microservices** — independent Order and Payment services
- **REST communication** — explicit service boundaries
- **MCP** — controlled AI-to-system tool interface
- **Distributed failure handling** — timeouts, unavailable services, inconsistent state
- **Database-per-service** — isolated PostgreSQL persistence
- **AI-assisted investigation** — evidence-driven cross-service reasoning

## Tech Stack

**Backend:** Java 21, Spring Boot, REST, JPA/Hibernate  
**Database:** PostgreSQL  
**AI / Tooling:** Python, MCP, LLM  
**Frontend:** Angular, TypeScript  


## Project Structure

```text
ops-pilot/
├── order-service/
├── payment-service/
├── mcp-server/
├── mcp-agent/
└── mcp-ui/
```

## Future Work

- OpenTelemetry distributed tracing
- Prometheus + Grafana observability
- Automated remediation with human approval
- Containerized deployment
- Kubernetes

---

**Built to explore how AI agents can become useful operations assistants without bypassing the boundaries of distributed systems.**
